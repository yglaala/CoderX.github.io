package com.ygl.canteen.model;

import java.io.Serializable;

public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private float price;
    private String createdate;
    private String path;

    public Menu(int id, String name,  float price, String createdate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdate = createdate;
    }

    public Menu(int id) {
        this.id = id;
    }

    public Menu(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Menu() {
    }

    public String getPath() {
        return path;
    }

    public Menu setPath(String path) {
        this.path = path;
        return this;
    }

    public int getId() {
        return id;
    }

    public Menu setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Menu setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Menu setCreatedate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdate='" + createdate + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
