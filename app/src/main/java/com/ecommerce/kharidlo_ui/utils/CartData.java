package com.ecommerce.kharidlo_ui.utils;

import com.ecommerce.kharidlo_ui.model.Cart;
import com.ecommerce.kharidlo_ui.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartData {
    private List<CartItem> cartItems;

    private static CartData cartDataInstance;

    public static CartData getInstance() {
        if(cartDataInstance == null){
            cartDataInstance = new CartData();
        }

        return cartDataInstance;
    }

    private CartData() {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addItemToCart(CartItem item) {
        this.cartItems.add(item);
    }

    public void emptyCart() {
        this.cartItems.clear();
    }
}
