package com.ecommerce.kharidlo_ui.remote;

import com.ecommerce.kharidlo_ui.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserRegistrationApi {
    @POST("register")
    Call<Integer> register(@Body User user);
}
