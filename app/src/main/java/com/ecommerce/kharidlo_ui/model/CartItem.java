package com.ecommerce.kharidlo_ui.model;

public class CartItem {
    private int productId;
    private int quantity;
    private double price;

    public CartItem(int productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
//{
//
//        "items": [
//        {
//        "productId":102,
//        "quantity":12,
//        "price":109.23
//        },
//        {
//        "productId":103,
//        "quantity":12,
//        "price":109.23
//        }
//        ]
//}