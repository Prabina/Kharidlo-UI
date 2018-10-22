package com.ecommerce.kharidlo_ui.remote;

import com.ecommerce.kharidlo_ui.model.AuthenticationCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginUserApi {
    @POST("login")
    Call<Object> login(@Body AuthenticationCredentials authenticationCredentials);
}
