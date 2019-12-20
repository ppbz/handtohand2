package com.sjtu.handtohandnew.Bean;

public class AddressBean {
    private String name;
    private String number;
    private String sex;
    private String address;

    public AddressBean() {
    }

    public AddressBean(String name, String number, String sex, String address) {
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
