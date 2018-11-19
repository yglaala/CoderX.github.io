package com.ygl.canteen.model;

import com.ygl.canteen.model.Menu;

public class OrderMenu {

    private int id;
    private Order order;
    private Menu menu;
    private int num;
    private float price;

    public OrderMenu() {
    }

    public int getId() {
        return id;
    }

    public OrderMenu setId(int id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderMenu setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    public OrderMenu setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public int getNum() {
        return num;
    }

    public OrderMenu setNum(int num) {
        this.num = num;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public OrderMenu setPrice(float price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "id=" + id +
                ", order=" + order +
                ", menu=" + menu +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}
