package com.ygl.canteen.service;

import com.ygl.canteen.dto.DeptCost;
import com.ygl.canteen.dto.DeptCosts;
import com.ygl.canteen.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {

    int add(Order order) throws SQLException;
    List<Order> getEmpOrders(int empId);
    List<Order> getOrders();
    int updateAdvanceTime(int time);
    int updateStatus(int statusId,String orderno);
    int _updateStatus(String orderno , int id);
    String[] getTimes();
    int getTime();
    String getMaxId();
    List<Order> getOrdersByEmpId(int empId);
    Order getOrderByOrderno(String orderno);
    List<Order> getEmpCost(int empId);
    DeptCosts getDeptCostByDate(String date);
}
