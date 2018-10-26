package com.ecommerce.kharidlo_ui.viewmodel;

import com.ecommerce.kharidlo_ui.model.Cart;
import com.ecommerce.kharidlo_ui.model.User;
import com.ecommerce.kharidlo_ui.remote.ICheckoutCartApi;
import com.ecommerce.kharidlo_ui.remote.IUserRegistrationApi;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CheckoutCartViewModel {
    private ICheckoutCartApi iCheckoutCartApi;

    public CheckoutCartViewModel(){
        Retrofit retrofit = RetrofitManager.getClient();
        iCheckoutCartApi = retrofit.create(ICheckoutCartApi.class);
    }

    public void checkoutCart(String token, Cart cart, Callback<JSONObject> callback) {

        Call<JSONObject> response = iCheckoutCartApi.checkoutCart(token, cart);
        response.enqueue(callback);

    }
}
