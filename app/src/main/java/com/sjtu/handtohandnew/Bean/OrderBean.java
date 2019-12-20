package com.sjtu.handtohandnew.Bean;

public class OrderBean {
    private String orderName;
    private int orderIcon;

    public OrderBean() {
    }

    public OrderBean(String orderName, int orderIcon) {
        this.orderName = orderName;
        this.orderIcon = orderIcon;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderIcon() {
        return orderIcon;
    }

    public void setOrderIcon(int orderIcon) {
        this.orderIcon = orderIcon;
    }
}
