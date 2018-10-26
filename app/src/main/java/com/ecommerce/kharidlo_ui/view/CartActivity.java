package com.ecommerce.kharidlo_ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.CartItem;
import com.ecommerce.kharidlo_ui.utils.CartData;
import com.ecommerce.kharidlo_ui.view.adapters.CartItemsAdapter;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private Button proceedToPayView;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = (ListView) findViewById(R.id.cart_list);
        List<CartItem> cartItems = CartData.getInstance().getCartItems();


        proceedToPayView = (Button) findViewById(R.id.proceedToPay);

        CartItemsAdapter adapter = new CartItemsAdapter(CartData.getInstance().getCartItems(),this);
        cartListView.setAdapter(adapter);


        proceedToPayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCheckoutScreen();
            }
        });
    }

    private void navigateToCheckoutScreen() {
        Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }
}
