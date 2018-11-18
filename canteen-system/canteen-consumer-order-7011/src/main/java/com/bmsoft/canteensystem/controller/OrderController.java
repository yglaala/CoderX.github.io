package com.bmsoft.canteensystem.controller;

import com.alibaba.fastjson.JSON;
import com.bmsoft.canteensystem.service.OrderClientService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
public class OrderController {

    @Autowired
    private OrderClientService orderClientService;

    @Autowired
    private HttpServletRequest request;
    private Map<String,Object> getSession(){
        Map<String, Object> user = (Map<String,Object>)request.getSession().getAttribute("userInfo");
        return user;
    }

    @RequestMapping("/checkface")
    public Msg checkFace( String snapData){
        //System.out.println(snapData);
        if(snapData == null && snapData == ""){
            return Msg.fail().setMsg("未获取到图片");
        }
        System.out.println(getSession());
        Integer userId = (Integer) getSession().get("userId");
        return orderClientService.checkFace(snapData , userId);
    }
    //添加订单
    @RequestMapping(value = "/add")
    public Msg addOrder(String orderMenus)  {


        String user = JSON.toJSONString(getSession());

        return orderClientService.addOrder(orderMenus,user);

    }
    //查询订单
    @RequestMapping("/list")
    public Msg getOrders( String start, String end, Integer statusId, Integer pageSize, Integer currentPage){

        String user = JSON.toJSONString(getSession());
        if(start == null){
            start = "0000-00-01";
        }
        if(end == null){
            end = "9999-12-31";
        }
        if(statusId == null){
            statusId = 0;
        }
        if(currentPage == null){
            currentPage = 1;
        }
        if(pageSize == null){
            pageSize = 5;
        }
        return orderClientService.getOrders(start,end,statusId,pageSize,currentPage,user);

    }
    //订单详情
    @RequestMapping("/details/{orderNo}")
    public Msg getOrderDetails(@PathVariable("orderNo") String orderNo){

        String user = JSON.toJSONString(getSession());
        System.out.println(user);
        return orderClientService.getOrderDetails(orderNo,user);
    }
    //更改订单状态
    @RequestMapping("/update/{statusId}/{orderNo}")
    public Msg updateOrderStatus(@PathVariable("orderNo") String orderNo, @PathVariable("statusId") int statusId){

        String user = JSON.toJSONString(getSession());

        return orderClientService.updateOrderStatus(orderNo,statusId,user);
    }


    //订单菜品
    @RequestMapping("/menus/{orderNo}")
    public Msg getOrderMenus(@PathVariable("orderNo") String orderNo){

        return orderClientService.getOrderMenus(orderNo);
    }

    //菜品建议
    @RequestMapping("/appraise")
    public Msg addAppraise(@RequestParam("menuSugs") String menuSugs, @RequestParam("orderNo") String orderNo){

        String user = JSON.toJSONString(getSession());
        return orderClientService.addAppraise(menuSugs,orderNo,user);
    }

    //初始化订单状态
    @RequestMapping("/status")
    public Msg getStatus(){

        return orderClientService.getStatus();
    }

    @RequestMapping("/checkface/{orderNo}")
    public Msg checkFaceByOrderNo(String snapData ,@PathVariable("orderNo") String orderNo ){
        if(snapData == null && snapData == ""){
            return Msg.fail().setMsg("未获取到图片");
        }
        return orderClientService.checkFaceByOrderNo(snapData,orderNo);
    }

    @RequestMapping("/menusale")
    public Msg getMenuSales(String start , String end){
        Integer userId = (Integer) getSession().get("userId");
        if(start == null || start.trim() == ""){
            start = "0000-00-01";
        }
        if(end == null || end.trim() == ""){
            end = "9999-12-31";
        }
//        start += " 00:00:00";
//        end += " 11:59:59";
       return orderClientService.getMenuSales(userId,start,end);
    }
}
