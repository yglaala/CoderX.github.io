package com.ygl.canteen.model;

/**
 * 订单状态表
 */
public class Status {

    private int id;
    private String name;


    public Status(int id) {
        this.id = id;
    }

    public Status() {
    }

    public int getId() {
        return id;
    }

    public Status setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Status setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
