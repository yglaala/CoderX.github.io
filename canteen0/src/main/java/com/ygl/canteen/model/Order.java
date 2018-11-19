package com.ygl.canteen.model;

import java.util.List;

public class Order {

    private int id;
    private String orderno;
    private Emp emp;
    private String createdate;
    private String orderdate;
    private Status status1;//状态1 食堂管理员状态
    private Status status2;//状态2 员工状态
    private List<OrderMenu> orderMenus;
    private int cost;
    private String costdate;

    public Order() {
    }

    public Order(String orderno, Status status1, Status status2) {
        this.orderno = orderno;
        this.status1 = status1;
        this.status2 = status2;
    }

    public String getCostdate() {
        return costdate;
    }

    public Order setCostdate(String costdate) {
        this.costdate = costdate;
        return this;
    }

    public Order(String orderno) {
        this.orderno = orderno;
    }

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public String getOrderno() {
        return orderno;
    }

    public Order setOrderno(String orderno) {
        this.orderno = orderno;
        return this;
    }

    public Emp getEmp() {
        return emp;
    }

    public Order setEmp(Emp emp) {
        this.emp = emp;
        return this;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Order setCreatedate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public Order setOrderdate(String orderdate) {
        this.orderdate = orderdate;
        return this;
    }

    public Status getStatus1() {
        return status1;
    }

    public Order setStatus1(Status status1) {
        this.status1 = status1;
        return this;
    }

    public Status getStatus2() {
        return status2;
    }

    public Order setStatus2(Status status2) {
        this.status2 = status2;
        return this;
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public Order setOrderMenus(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public Order setCost(int cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderno='" + orderno + '\'' +
                ", emp=" + emp +
                ", createdate='" + createdate + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", status1=" + status1 +
                ", status2=" + status2 +
                ", orderMenus=" + orderMenus +
                ", cost=" + cost +
                '}';
    }
}
