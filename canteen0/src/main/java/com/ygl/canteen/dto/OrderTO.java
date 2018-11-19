package com.ygl.canteen.dto;

import com.ygl.canteen.model.Emp;
import com.ygl.canteen.model.Menu;
import com.ygl.canteen.model.Status;


import java.util.List;
import java.util.Map;

public class OrderTO {

    private List<MenuTO> menuTOs;
    private String orderno;
    private Emp emp;
    private String orderdate;
    private Status status;
    private String createdate;

    public List<MenuTO> getMenuTOs() {
        return menuTOs;
    }

    public OrderTO setMenuTOs(List<MenuTO> menuTOs) {
        this.menuTOs = menuTOs;
        return this;
    }

    public String getOrderno() {
        return orderno;
    }

    public OrderTO setOrderno(String orderno) {
        this.orderno = orderno;
        return this;
    }

    public Emp getEmp() {
        return emp;
    }

    public OrderTO setEmp(Emp emp) {
        this.emp = emp;
        return this;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public OrderTO setOrderdate(String orderdate) {
        this.orderdate = orderdate;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public OrderTO setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getCreatedate() {
        return createdate;
    }

    public OrderTO setCreatedate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public OrderTO() {
    }
}
