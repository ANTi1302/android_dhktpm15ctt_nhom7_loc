package com.example.nhom7.Model;

import java.util.List;

public class Bill {
    private String phone;
    private String address;
    private String name;
    private String total;
    private List<Order> foods;

    public Bill() {
    }

    public Bill(String phone, String address, String name, String total, List<Order> foods) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.total = total;
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
