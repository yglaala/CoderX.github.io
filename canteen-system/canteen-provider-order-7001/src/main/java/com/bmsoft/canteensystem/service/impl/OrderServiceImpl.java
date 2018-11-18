package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.dto.*;
import com.bmsoft.canteensystem.entity.Order;
import com.bmsoft.canteensystem.entity.OrderMenu;
import com.bmsoft.canteensystem.entity.OrderStatus;
import com.bmsoft.canteensystem.mapper.*;
import com.bmsoft.canteensystem.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderMenuMapper orderMenuMapper;

    @Autowired
    private MatchMenuMapper matchMenuMapper;

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Autowired
    private MenuSaleMapper menuSaleMapper;

    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public boolean addOrder(Order order) {

        int count = orderMapper.add(order);

        Map<String, Object> params = null;
        for (OrderMenu orderMenu : order.getOrderMenus()) {
            try {
                count += orderMenuMapper.addOrderMenu(orderMenu.setOrderNo(order.getOrderNo()));

                //减少对应菜品的数量
                params = new HashMap<>();
                params.put("orderMenuNum", orderMenu.getOrderMenuNum() * (-1));
                params.put("matchMenuId", orderMenu.getMatchMenu().getMatchMenuId());
                count += matchMenuMapper.updateMatchMenuTime(params);

                //删除购物车中相关记录
                params.put("userId", order.getUser().getUserId());
                shoppingCarMapper.deleteShopCarByMatchId(params);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        if (count < 2 * (order.getOrderMenus().size()) + 1) {
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public PageInfo<OrderDto> getOrders(Map<String, Object> params) {

        Integer currentPage = (params.get("currentPage") == null) ? 1 : (Integer) params.get("currentPage");
        Integer pageSize = (params.get("pageSize") == null) ? 5 : (Integer) params.get("pageSize");

        return PageHelper.startPage(currentPage, pageSize).doSelectPageInfo(() -> orderMapper.getOrders(params));
    }

    @Override
    public Map<String,Object> getRemainMenu(Integer matchMenuId) {
        return orderMapper.getRemainMenu(matchMenuId);
    }

    @Override
    public OrderDto getOrderDetails(String orderNo) {
        return orderMapper.getOrderDetailsByOrderNo(orderNo);
    }

    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public boolean updateOrderStatus(Map<String, Object> params) {

        Integer targetStatusId = (Integer) params.get("statusId");
        Integer count = 0;
        try{
            count = orderMapper.updateOrderStatus(params);
            if (targetStatusId == 5 || targetStatusId == 6 || targetStatusId == 3) {
                count += orderMapper.returnMatchMenu((String) params.get("orderNo"));
            }
            if(targetStatusId == 4){
                count += menuSaleMapper.addMenuSaleByOrderNo((String)params.get("orderNo"));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

        if (count < 1) {
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public List<OrderMenuSugDto> getMenuSugsByOrderNo(String orderNo) {

        List<OrderMenuSugDto> orderMenuSugDtos = orderMapper.getOrderMenuSugsByOrderNo(orderNo);
        for(OrderMenuSugDto orderMenuSugDto : orderMenuSugDtos){
            orderMenuSugDto.setId();
        }
        return orderMenuSugDtos;
    }

    @Override
    public List<EmpConsumeDto> getEmpConsumes() {
        return orderMapper.getEmpConsume();
    }

    @Override
    public List<OrderStatus> getOrderStatuss() {
        return orderMapper.getOrderStatuss();
    }

    @Override
    public MatchMenuDto matchMenuIsValid(Integer matchMenuId) {
        return matchMenuMapper.selectMatchMenuById(matchMenuId).addInfo();
    }

    @Override
    public List<MenuSaleDto> getMenuSales(Map<String, Object> params) {
        return menuSaleMapper.getMenuSaleByParams(params);
    }

    @Override
    public boolean statusIdIsLegal(String orderNo , Integer statusId , Integer roleId) {

        Order order = orderMapper.getStatusIdByOrderNo(orderNo);
        Integer oldStatusId = order.getOrderStatus().getOrderStatusId();
        Integer orderPayStatus = order.getOrderPayStatus();
        //管理员
        if(roleId == 1){
            //待接单
            if(oldStatusId == 1){
                if(statusId != 2 && statusId != 3){
                    return false;
                }
            }
            //已接单
            else if(oldStatusId == 2){
                if(statusId != 4 && statusId != 5){
                    return false;
                }
            }
            //员工已删除订单
            else if(oldStatusId > 10 && oldStatusId <15){
                if(statusId != 16){
                    return false;
                }
            }
            else {
                if(statusId != (oldStatusId+4)){
                    return false;
                }
            }
        }
        else {
            if(oldStatusId == 1 || oldStatusId ==2){
                if(statusId != 6){
                    return false;
                }
            }else if(oldStatusId > 6 && oldStatusId < 11){
                if(statusId != 16){
                    return false;
                }
            } else {
                if(statusId != (oldStatusId+8)){
                    return false;
                }
            }
            if(orderPayStatus == 1){
                if(oldStatusId > 6 && oldStatusId < 11){
                    if(statusId != 16){
                        return false;
                    }
                }else {
                    if(statusId != oldStatusId+8){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean orderIsCancel(String orderNo) {
        return orderMapper.getShopTimeByOrderNo(orderNo).addStatusInfo().getValid();
    }
}
