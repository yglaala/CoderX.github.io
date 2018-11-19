package com.ygl.canteen.dto;

public class DeptCost {

    private String name;
    private String orderNums;
    private float totalCost;

    public String getName() {
        return name;
    }

    public DeptCost setName(String name) {
        this.name = name;
        return this;
    }

    public String getOrderNums() {
        return orderNums;
    }

    public DeptCost setOrderNums(String orderNums) {
        this.orderNums = orderNums;
        return this;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public DeptCost setTotalCost(float totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    @Override
    public String toString() {
        return "DeptCost{" +
                "name='" + name + '\'' +
                ", orderNums='" + orderNums + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
