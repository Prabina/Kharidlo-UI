package com.ecommerce.kharidlo_ui.model;

import java.util.List;

public class Cart {
    private String emailId;
    private double totalPrice;
    private String address;
    private String fullName;
    private List<CartItem> items;

    public Cart(String emailId, String fullName, double totalPrice, String address, List<CartItem> items) {
        this.emailId = emailId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.items = items;
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
