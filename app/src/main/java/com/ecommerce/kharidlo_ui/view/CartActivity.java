package com.ecommerce.kharidlo_ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ecommerce.kharidlo_ui.R;

public class CartActivity extends AppCompatActivity {
    private Button proceedToPayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        proceedToPayView = (Button) findViewById(R.id.proceedToPay);
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
