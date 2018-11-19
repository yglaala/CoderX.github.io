package com.ygl.canteen.service.impl;

import com.ygl.canteen.dto.DeptCost;
import com.ygl.canteen.dto.DeptCosts;
import com.ygl.canteen.mapper.OrderMapper;
import com.ygl.canteen.model.Order;
import com.ygl.canteen.model.OrderMenu;
import com.ygl.canteen.model.Status;
import com.ygl.canteen.service.IOrderService;
import com.ygl.canteen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 插入订单
     * @param order
     * @return
     * @throws SQLException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED , propagation = Propagation.REQUIRED)
    @Override
    public int add(Order order) throws SQLException {

        int i = orderMapper.add(order);
        for(OrderMenu orderMenu : order.getOrderMenus()){
            orderMenu.setPrice(orderMapper.getPriceByMenu(orderMenu.getMenu().getId()));
            i += orderMapper.addOrderMenu(orderMenu);
        }
        return i;
    }

    /**
     * 通过员工号获取订单
     * @param empId
     * @return
     */
    @Override
    public List<Order> getEmpOrders(int empId) {

        return null;
    }

    /**
     * 获取所有订单
     * @return
     */
    @Override
    public List<Order> getOrders() {
        return orderMapper.getOrders();
    }

    /**
     * 更新订餐时间
     * @param time
     * @return
     */
    @Override
    public int updateAdvanceTime(int time) {
        return orderMapper.updateAdvanceTime(time);
    }

    /**
     * 更新订单公有状态
     * @param orderno
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public int updateStatus(int statusId,String orderno) {

        int i = orderMapper.updateStatus1(new Order(orderno,new Status(statusId),null));
        i += orderMapper.updateStatus2(new Order(orderno,null,new Status(statusId)));
        if(statusId == 2){
            i += orderMapper.updateCost(orderno);
        }
        return i;
    }

    /**
     * 更新订单私有状态
     * @param orderno
     * @param id
     * @return
     */
    @Override
    public int _updateStatus(String orderno, int id) {
        if(id == 1){
            return orderMapper.updateStatus1(new Order(orderno,new Status(5),null));
        }
        return orderMapper.updateStatus2(new Order(orderno,null,new Status(5)));
    }


    /**
     * 获取可订餐的时间段
     * @return
     */
    @Override
    public String[] getTimes() {

        return DateUtil.getDates(orderMapper.getMaxTime());
    }

    @Override
    public int getTime() {
        return orderMapper.getMaxTime();
    }

    @Override
    public String getMaxId() {
        return orderMapper.getMaxId();
    }

    @Override
    public List<Order> getOrdersByEmpId(int empId) {
        return orderMapper.getOrdersByEmpId(empId);
    }

    @Override
    public Order getOrderByOrderno(String orderno) {

        Order order = orderMapper.getEmpOrdersDetails(orderno);
        return order;
    }

    @Override
    public List<Order> getEmpCost(int empId) {
        return orderMapper.getOrderByEmpCost(empId);
    }

    @Override
    public DeptCosts getDeptCostByDate(String date) {

        DeptCosts deptCosts = new DeptCosts();
        List<DeptCost> deptCostList = orderMapper.getDeptCostByDate(date);
        deptCosts.setDate(date);
        deptCosts.setDeptCostList(deptCostList);
        return deptCosts;
    }
}
