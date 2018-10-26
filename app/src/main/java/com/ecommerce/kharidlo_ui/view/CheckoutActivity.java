package com.ecommerce.kharidlo_ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.Cart;
import com.ecommerce.kharidlo_ui.model.CartItem;
import com.ecommerce.kharidlo_ui.utils.CartData;
import com.ecommerce.kharidlo_ui.utils.SharedPreferenceUtil;
import com.ecommerce.kharidlo_ui.viewmodel.CheckoutCartViewModel;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    private EditText fullNameView;
    private EditText phoneNumberView;
    private EditText addressView;
    private RadioButton paymentOptionView;
    private EditText emailIdView;
    private CartData cartData = CartData.getInstance();
    private CheckoutCartViewModel checkoutCartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        fullNameView = (EditText) findViewById(R.id.fullName);
        phoneNumberView = (EditText) findViewById(R.id.phoneNumber);
        addressView = (EditText) findViewById(R.id.address);
        paymentOptionView = (RadioButton) findViewById(R.id.radioCOD);
        emailIdView = (EditText) findViewById(R.id.enailId);
        checkoutCartViewModel = new CheckoutCartViewModel();

        fullNameView.setError(null);
        phoneNumberView.setError(null);
        addressView.setError(null);
        paymentOptionView.setError(null);
        emailIdView.setError(null);
    }

    public void placeOrder(View view) {
        if (!isFormValid()) {
            return;
        }

        List<CartItem> cartItems = cartData.getCartItems();
        String fullName = fullNameView.getText().toString();
        String phoneNumber = phoneNumberView.getText().toString();
        String address = addressView.getText().toString();
        String emailId = emailIdView.getText().toString();
        double totalPrice = calculateTotalPrice(cartItems);

        Cart cart = new Cart(emailId, fullName, totalPrice, address, cartItems);
        String token = SharedPreferenceUtil.getTOKEN();
        checkoutCartViewModel.checkoutCart(token, cart, new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.code() == 403) {
                    cartData.emptyCart();
                    SharedPreferenceUtil.clearUserData();
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(CheckoutActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (response.code() == 201) {
                    cartData.emptyCart();
                    Toast toast = Toast.makeText(getApplicationContext(), (String)((LinkedTreeMap) response.body()).get("successMessage"), Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(CheckoutActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Something has gone wrong. Please contact admin!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                System.out.print("ERROR");
            }
        });
    }

    private double calculateTotalPrice(List<CartItem> cartItems) {
        double total = 0;

        for(CartItem item : cartItems) {
            total += (item.getPrice() * item.getQuantity());
        }

        return total;
    }

    private boolean isFormValid() {
        boolean isFormValid = true;
        String fullName = fullNameView.getText().toString();
        String phoneNumber = phoneNumberView.getText().toString();
        String address = addressView.getText().toString();

        if (!isValidString(fullName)) {
            isFormValid = false;
            fullNameView.setError("Please enter your name");
        }
        if (!isValidString(phoneNumber)) {
            isFormValid = false;
            phoneNumberView.setError("Please enter valid phone number");
        }
        if (!isValidString(address)) {
            isFormValid = false;
            addressView.setError("Please enter address");
        }
        return isFormValid;
    }

    private boolean isValidString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return true;
    }
}
