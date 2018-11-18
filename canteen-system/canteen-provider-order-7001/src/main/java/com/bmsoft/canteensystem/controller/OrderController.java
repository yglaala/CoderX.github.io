package com.bmsoft.canteensystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bmsoft.canteensystem.dto.MatchMenuDto;
import com.bmsoft.canteensystem.dto.OrderDto;
import com.bmsoft.canteensystem.dto.OrderMenuSugDto;
import com.bmsoft.canteensystem.entity.*;
import com.bmsoft.canteensystem.service.MenuSugService;
import com.bmsoft.canteensystem.service.OrderService;
import com.bmsoft.canteensystem.service.UserService;
import com.bmsoft.canteensystem.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MenuSugService menuSugService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    private Msg checkStatus(String orderNo , Integer statusId){


        return Msg.fail();
    }
    //人脸比对
    @RequestMapping("/order/checkface")
    public Msg checkFace(@RequestParam("snapData") String snapData , @RequestParam("userId") Integer userId){

        String urlB = userService.getPic(userId);
        //System.out.println(urlB);
        MultiValueMap<String,String> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("snapData",snapData);
        postParameters.add("urlB",urlB);

        JSONObject result = JSONObject.parseObject(restTemplate.postForObject(Constants.ADDRESS_COMPARE,postParameters,String.class));

        System.out.println(result);
        Msg msg = new Msg();

        if(result.get("code").equals(0)){
            float similarity = 0;
            try{
                similarity = StringUtil.strToFlo(JSONObject.parseObject(result.get("data").toString()).
                        get("similarity").toString());
            }
            catch (Exception e){}
            if(similarity > 75){
                return msg.setCode(100).setMsg("人脸比对成功").add("info",result);
            }
            return msg.setCode(200).setMsg("人脸不符").add("info",result);
        }
        return msg.setCode(0).setMsg("比对出错").add("info",result);
    }

    //添加订单
    @RequestMapping(value = "/order/add")
    public Msg addOrder(String orderMenus , @RequestParam("user") String user0)  {


        User user = JSON.parseObject(user0,User.class);

        //人脸验证
//        Msg msg = checkFace(snapData,user.getUserId());
//        if(msg.getCode() != 100){
//            return msg;
//        }

        List<OrderMenu> orderMenuList = JSONObject.parseArray(orderMenus, OrderMenu.class);
        if(orderMenuList.size() == 0 || orderMenuList == null){
            return Msg.fail().setMsg("请至少选择一种菜品");
        }

        //List<String> menus = new ArrayList<>();
        for(OrderMenu orderMenu : orderMenuList){

            MatchMenuDto matchMenuDto = orderService.matchMenuIsValid(orderMenu.getMatchMenu().getMatchMenuId());
            if(!matchMenuDto.getValid()){
               return Msg.fail().setMsg("菜品"+matchMenuDto.getMenuName()+"信息已过期");
            }
        }

        boolean flag = true;
        for(OrderMenu orderMenu : orderMenuList){
            Map<String,Object> result = orderService.getRemainMenu(orderMenu.getMatchMenu().getMatchMenuId());
            if((Integer)result.get("match_menu_num") < orderMenu.getOrderMenuNum()){
                return Msg.fail().setMsg(""+result.get("menu_name")+"余量不足");
            }
        }
        if(!flag){
            return new Msg().setCode(200).setMsg("菜品余量不足");
        }
        //添加订单之前先判断菜品余量
        Order order = new Order();
        order.setOrderNo(user.getUserId()+ DateUtil.getDateStr()+ DateUtil.getTimeStr()).
                setUser(user).setOrderMenus(orderMenuList).setOrderPayStatus(0).
                setOrderStatus(new OrderStatus().setOrderStatusId(1));
        try {
            orderService.addOrder(order);
        } catch (Exception e) {
            return new Msg().setCode(200).setMsg("创建订单失败");
        }
        return new Msg().setCode(100).setMsg("创建订单成功");
    }
    //查询订单
    @RequestMapping("/order/list")
    public Msg getOrders(String start, String end, Integer statusId, Integer pageSize, Integer currentPage , @RequestParam("user") String user0){

        User user = JSON.parseObject(user0,User.class);
        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("end",end);
        params.put("statusId",statusId);
        params.put("user",user);
        params.put("pageSize",pageSize);
        params.put("currentPage",currentPage);

        Msg msg = Msg.success();
        msg.add("orders",orderService.getOrders(params));
        return msg;
    }
    //订单详情
    @RequestMapping("/order/details/{orderNo}")
    public Msg getOrderDetails(@PathVariable("orderNo") String orderNo ,@RequestParam("user") String user0){

        //session.setAttribute("user",new User().setRoleId(1).setUserId(2));
        //User user = (User)request.getSession().getAttribute("user");
        System.out.println(user0);
        User user = JSON.parseObject(user0,User.class);
        OrderDto orderDto = orderService.getOrderDetails(orderNo);
        orderDto.addBtns(user.getRoleId());
        if(orderDto != null){
            return Msg.success().add("orderDto",orderDto);
        }
        return Msg.fail();
    }
    //更改订单状态
    @RequestMapping("/order/update/{statusId}/{orderNo}")
    public Msg updateOrderStatus(@PathVariable("orderNo") String orderNo, @PathVariable("statusId") int statusId,@RequestParam("user") String user0){

        User user = JSON.parseObject(user0,User.class);
        if(statusId == 6){
            if(!orderService.orderIsCancel(orderNo)){
                return Msg.fail().setMsg("该订单现在无法取消");
            }
        }
        //人脸验证
//        if(statusId == 4){
//            Msg msg = checkFace(snapData,user.getUserId());
//            if(msg.getCode() != 100){
//                return msg;
//            }
//        }
        if(!orderService.statusIdIsLegal(orderNo,statusId,user.getRoleId())){
           return Msg.fail().setMsg("不合法的操作，请刷新页面");
        }
        Map<String,Object> params = new HashMap<>();
        //System.out.println(orderNo);
        params.put("orderNo",orderNo);
        params.put("statusId",statusId);
        try {
            orderService.updateOrderStatus(params);
        }catch (Exception e){
            return Msg.fail();
        }
        return Msg.success();
    }

    @RequestMapping("/order/checkface/{orderNo}")
    public Msg checkFaceByOrderNo(@RequestParam("snapData") String snapData ,@PathVariable("orderNo") String orderNo ){

        String urlB = userService.getPicByUserOrderNo(orderNo);
        //System.out.println(urlB);
        MultiValueMap<String,String> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("snapData",snapData);
        postParameters.add("urlB",urlB);

        JSONObject result = JSONObject.parseObject(restTemplate.postForObject(Constants.ADDRESS_COMPARE,postParameters,String.class));

        System.out.println(result);
        Msg msg = new Msg();

        if(result.get("code").equals(0)){
            float similarity = 0;
            try{
                similarity = StringUtil.strToFlo(JSONObject.parseObject(result.get("data").toString()).
                        get("similarity").toString());
            }
            catch (Exception e){}
            if(similarity > 75){
                return msg.setCode(100).setMsg("人脸比对成功").add("info",result);
            }
            return msg.setCode(200).setMsg("人脸不符").add("info",result);
        }
        return msg.setCode(0).setMsg("比对出错").add("info",result);
    }

    //订单菜品
    @RequestMapping("/order/menus/{orderNo}")
    public Msg getOrderMenus(@PathVariable("orderNo") String orderNo){

        List<OrderMenuSugDto> orderMenuDtos = orderService.getMenuSugsByOrderNo(orderNo);
        //System.out.println(orderMenuDtos);
        if(orderMenuDtos == null){
            return Msg.fail();
        }
        return Msg.success().add("orderMenuDtos",orderMenuDtos);
    }

    //菜品建议
    @RequestMapping("/order/appraise")
    public Msg addAppraise(String menuSugs,String orderNo,@RequestParam("user") String user0){

        User user = JSON.parseObject(user0,User.class);
        List<MenuSug> menuSugList = JSONObject.parseArray(menuSugs,MenuSug.class);
        for(MenuSug menuSug : menuSugList){
            menuSug.setUser(user).setOrderNo(orderNo);
        }
        try {
            menuSugService.addMenuSugs(menuSugList);
        }catch (Exception e){
            //e.printStackTrace();
            return Msg.fail().setMsg("添加建议失败");
        }
        /*JSONObject.parseArray(menuSugs , )*/
        return Msg.success().setMsg("添加建议成功");
    }

    //初始化订单状态
    @RequestMapping("/order/status")
    public Msg getStatus(){

        List<OrderStatus> orderStatuses = orderService.getOrderStatuss();
        if(orderStatuses == null){
            return Msg.fail().setMsg("订单状态初始化失败");
        }
        return Msg.success().add("orderStatuses",orderStatuses);
    }

//    @RequestMapping("get")
//    public Object get(){
//        return request.getSession().getAttribute("admin");
//    }
    @RequestMapping("/order/menusale")
    public Msg getMenuSales(Integer userId , String start , String end){

        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("start",start);
        params.put("end",end);
        return Msg.success().add("menuSale",orderService.getMenuSales(params));
    }
}
