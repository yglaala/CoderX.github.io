package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.dto.*;
import com.bmsoft.canteensystem.entity.Order;
import com.bmsoft.canteensystem.entity.OrderStatus;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    boolean addOrder(Order order) throws Exception;

    PageInfo<OrderDto> getOrders(Map<String , Object> params);

    Map<String,Object> getRemainMenu(Integer matchMenuId);

    OrderDto getOrderDetails(String orderNo);

    boolean updateOrderStatus(Map<String,Object> params);

    List<OrderMenuSugDto> getMenuSugsByOrderNo(String orderNo);

    List<EmpConsumeDto> getEmpConsumes();

    List<OrderStatus> getOrderStatuss();

    MatchMenuDto matchMenuIsValid(Integer matchMenuId);

    List<MenuSaleDto> getMenuSales(Map<String,Object> params);

    boolean statusIdIsLegal(String orderNo , Integer statusId , Integer roleId);

    boolean orderIsCancel(String orderNo);
}
