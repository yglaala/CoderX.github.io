package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.util.Msg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ORDER-PROVIDER",fallbackFactory = OrderClientFallbackFactory.class )
public interface OrderClientService  {


    @RequestMapping("/order/checkface")
    Msg checkFace(@RequestParam("snapData") String snapData, @RequestParam("userId") Integer userId);

    //添加订单
    @RequestMapping(value = "/order/add")
    Msg addOrder(@RequestParam("orderMenus") String orderMenus, @RequestParam("user") String user);

    //查询订单
    @RequestMapping("/order/list")
    Msg getOrders(@RequestParam("start") String start,
                  @RequestParam("end") String end,
                  @RequestParam("statusId") Integer statusId,
                  @RequestParam("pageSize") Integer pageSize,
                  @RequestParam("currentPage") Integer currentPage,
                  @RequestParam("user") String user);

    //订单详情
    @RequestMapping("/order/details/{orderNo}")
    Msg getOrderDetails(@PathVariable("orderNo") String orderNo, @RequestParam("user") String user);

    //更改订单状态
    @RequestMapping("/order/update/{statusId}/{orderNo}")
    Msg updateOrderStatus(@PathVariable("orderNo") String orderNo, @PathVariable("statusId") int statusId, @RequestParam("user") String user);

    @RequestMapping("/order/checkface/{orderNo}")
    Msg checkFaceByOrderNo(@RequestParam("snapData") String snapData, @PathVariable("orderNo") String orderNo);

    //订单菜品
    @RequestMapping("/order/menus/{orderNo}")
    Msg getOrderMenus(@PathVariable("orderNo") String orderNo);

    //菜品建议
    @RequestMapping("/order/appraise")
    Msg addAppraise(@RequestParam("menuSugs") String menuSugs, @RequestParam("orderNo") String orderNo, @RequestParam("user") String user);

    //初始化订单状态
    @RequestMapping("/order/status")
    Msg getStatus();

    @RequestMapping("/order/menusale")
    Msg getMenuSales(@RequestParam("userId") Integer userId, @RequestParam("start") String start, @RequestParam("end") String end);
}
