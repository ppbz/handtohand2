package com.sjtu.handtohandnew.Bean;

public class CommodityBean {
    private String commodityName;
    private String commodityNumber;
    private String commodityMoney;
    private int commodityIcon;

    public CommodityBean() {
    }

    public CommodityBean(String commodityName, String commodityNumber, String commodityMoney, int commodityIcon) {
        this.commodityName = commodityName;
        this.commodityNumber = commodityNumber;
        this.commodityMoney = commodityMoney;
        this.commodityIcon = commodityIcon;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(String commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public String getCommodityMoney() {
        return commodityMoney;
    }

    public void setCommodityMoney(String commodityMoney) {
        this.commodityMoney = commodityMoney;
    }

    public int getCommodityIcon() {
        return commodityIcon;
    }

    public void setCommodityIcon(int commodityIcon) {
        this.commodityIcon = commodityIcon;
    }
}
