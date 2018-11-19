package com.ygl.canteen.dto;

public class MenuTO {

    private String name;
    private float price;
    private int num;

    public MenuTO() {
    }

    public String getName() {
        return name;
    }

    public MenuTO setName(String name) {
        this.name = name;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public MenuTO setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getNum() {
        return num;
    }

    public MenuTO setNum(int num) {
        this.num = num;
        return this;
    }

    @Override
    public String toString() {
        return "MenuTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
