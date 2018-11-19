package com.ygl.canteen.controller;

import com.ygl.canteen.dto.DeptCost;
import com.ygl.canteen.dto.DeptCosts;
import com.ygl.canteen.model.*;
import com.ygl.canteen.service.IOrderService;
import com.ygl.canteen.util.DateUtil;
import com.ygl.canteen.util.Msg;
import com.ygl.canteen.util.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/emp/menu/order/add")
    public String add() {
        return "emp/menu/order/add";
    }

    @GetMapping("/emp/menu/order/getOrders")
    public String getOrders() {
        return "emp/menu/order/listOrders";
    }

    @GetMapping("/emp/menu/order/getOrdersemp")
    public String getEmpOrders() {
        return "emp/menu/order/list";
    }

    @GetMapping("/emp/menu/order/details/{orderno}")
    public String details(@PathVariable("orderno") String orderno, HttpServletRequest request) {

        request.setAttribute("orderno", orderno);
        return "emp/menu/order/details";
    }

    @GetMapping("/emp/menu/order/listcostemp")
    public String listCostEmp() {
        return "/emp/menu/order/costlist";
    }

    @GetMapping("/emp/menu/order/listcost")
    public String listCost() {
        return "/emp/menu/order/deptcostlist";
    }

    @PostMapping("/emp/menu/order/listcost0")
    @ResponseBody
    public DeptCosts getDeptCosts(String date) {

        return orderService.getDeptCostByDate(date);
    }

    @PostMapping("/emp/menu/order/listcostemp")
    @ResponseBody
    public List<Order> listEmpCost(HttpServletRequest request) {
        Emp emp = (Emp) request.getSession().getAttribute("emp");
        /*System.out.println(emp.getId());*/
        List<Order> orders = orderService.getEmpCost(emp.getId());
        System.out.println(orders);
        return orders;
    }

    @PostMapping("/emp/menu/order/insert")
    @ResponseBody
    public Msg insert(String idnums, String date, HttpServletRequest request) throws SQLException {

        idnums = idnums.substring(1, idnums.length() - 1);
        String idnum[] = idnums.split(",");

        Order order = new Order();
        order.setOrderdate(date);
        order.setStatus1(new Status(1));
        order.setStatus2(new Status(1));
        order.setCost(0);
        order.setEmp((Emp) request.getSession().getAttribute("emp"));
        //生成订单编号
        String maxId = (orderService.getMaxId() == null ? "1" : orderService.getMaxId()) + order.getEmp().getId();
        String dateTime = DateUtil.getDateStr() + DateUtil.getTimeStr();
        order.setOrderno(maxId + dateTime);

        List<OrderMenu> orderMenus = new ArrayList<>();
        OrderMenu orderMenu = null;
        for (String id_num : idnum) {
            if (StringUtil.StringToInt(id_num.split(":")[1]) != 0) {
                orderMenu = new OrderMenu();
                orderMenu.setOrder(new Order(maxId + dateTime));
                orderMenu.setMenu(new Menu(StringUtil.StringToInt(id_num.split(":")[0].replace("\"", "").replace("\"", ""))));
                orderMenu.setNum(StringUtil.StringToInt(id_num.split(":")[1]));
                orderMenus.add(orderMenu);
            }
        }
        order.setOrderMenus(orderMenus);
        if (orderService.add(order) < order.getOrderMenus().size() + 1) {
            return new Msg(100, "添加失败");
        }
        return new Msg(200, "添加成功");
    }

    @PostMapping("/emp/menu/order/listemp")
    @ResponseBody
    public List<Order> getEmpOrders(HttpServletRequest request) {

        Emp emp = (Emp) request.getSession().getAttribute("emp");
        List<Order> orders = orderService.getOrdersByEmpId(emp.getId());
        System.out.println(orders);
        return orders;
    }

    @PostMapping("/emp/menu/order/list")
    @ResponseBody
    public List<Order> getOrders(HttpServletRequest request) {

        return orderService.getOrders();
    }

    @PostMapping("/emp/menu/order/details")
    @ResponseBody
    public Order getDetails(String orderno) {

        return orderService.getOrderByOrderno(orderno);
    }

    @ResponseBody
    @PostMapping("/emp/menu/order/getTimes")
    public String[] getTimes() {

        return orderService.getTimes();
    }

    @GetMapping("/emp/menu/order/updateStatus/{orderno}/{statusId}")
    @ResponseBody
    public Msg updateStatus(@PathVariable String orderno,
                            @PathVariable int statusId) {

        System.out.println(orderno + "  " + statusId);
        if (orderService.updateStatus(statusId, orderno) < 2) {
            return new Msg(100, "操作失败");
        }
        return new Msg(200, "操作成功");
    }

    @PostMapping("/emp/menu/order/delStatus/{orderno}/{code}")
    @ResponseBody
    public Msg delStatus(@PathVariable("orderno") String orderno,
                         @PathVariable("code") int code) {
        if (orderService._updateStatus(orderno, code) < 1) {
            return new Msg(100, "删除失败");
        }
        return new Msg(200, "删除成功");
    }

    @GetMapping("/emp/menu/order/downtoxls")
    /*@ResponseBody*/
    public void downToXls(@RequestParam("date") String date, HttpServletResponse response) throws IOException {
        //获取数据源
        DeptCosts deptCosts = orderService.getDeptCostByDate(date);
        List<DeptCost> deptCostList = deptCosts.getDeptCostList();

        //导出到excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(deptCosts.getDate());

        String fileName = deptCosts.getDate() + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;

        String[] headers = {"部门", "订单数量", "消费金额"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);

        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (DeptCost deptCost : deptCostList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(deptCost.getName());
            row1.createCell(1).setCellValue(deptCost.getOrderNums());
            row1.createCell(2).setCellValue(deptCost.getTotalCost());
            rowNum++;
        }

        fileName = URLEncoder.encode(fileName,"utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());

       /* return "/emp/menu/order/listcost";*/
        /*return new Msg(200, "导出成功");*/
    }

    @GetMapping("/emp/menu/order/getTime")
    @ResponseBody
    public int getTime(){
        /*System.out.println("lalalal");*/
        return orderService.getTime();
    }

    @GetMapping("/emp/menu/order/updateTime/{time}")
    @ResponseBody
    public Msg updateTime(@PathVariable("time") int time){

        if(orderService.updateAdvanceTime(time) < 1){
            return new Msg(100,"修改失败");
        }
        return new Msg(200,"修改成功");
    }
}
