package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.util.Msg;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderClientFallbackFactory implements FallbackFactory<OrderClientService> {


    @Override
    public OrderClientService create(Throwable throwable) {
        return new OrderClientService() {
            @Override
            public Msg checkFace(String snapData , Integer userId) {

                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg addOrder(String orderMenus, String snapData) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg getOrders(String start, String end, Integer statusId, Integer pageSize, Integer currentPage,String user) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg getOrderDetails(String orderNo , String user) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg updateOrderStatus(String orderNo, int statusId, String user) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg checkFaceByOrderNo(String snapData, String orderNo) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg getOrderMenus(String orderNo) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg addAppraise(String menuSugs, String orderNo ,String user) {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg getStatus() {
                return Msg.fail().setMsg("服务器错误");
            }

            @Override
            public Msg getMenuSales(Integer userId, String start, String end) {
                return Msg.fail().setMsg("服务器错误");
            }
        };
    }
}
