package com.ecommerce.kharidlo_ui.remote;

import com.ecommerce.kharidlo_ui.model.Cart;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ICheckoutCartApi {
    @POST("order")
    Call<Object> checkoutCart(@Header("Authorization") String token, @Body Cart cart);
}
