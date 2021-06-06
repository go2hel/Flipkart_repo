package com.sample.bean;

public class Item {
    private String name;
    private float price;
    private int count;

    public Item(String name, float price, int count){
        this.count=count;this.name=name;this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
