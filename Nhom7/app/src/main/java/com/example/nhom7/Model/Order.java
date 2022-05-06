package com.example.nhom7.Model;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Quality;
    private String Price;
    private String Discount;

    public Order(String productId, String productName, String quality, String price, String discount) {
        ProductId = productId;
        ProductName = productName;
        Quality = quality;
        Price = price;
        Discount = discount;
    }

    public Order() {
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
