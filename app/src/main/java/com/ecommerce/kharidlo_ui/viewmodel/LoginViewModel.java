package com.ecommerce.kharidlo_ui.viewmodel;

import com.ecommerce.kharidlo_ui.model.AuthenticationCredentials;
import com.ecommerce.kharidlo_ui.remote.ILoginUserApi;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginViewModel {

    private ILoginUserApi iLoginUserApi;

    public LoginViewModel(){
        Retrofit retrofit = RetrofitManager.getClient();
        iLoginUserApi = retrofit.create(ILoginUserApi.class);
    }

    public void login(AuthenticationCredentials authenticationCredentials, Callback<Object> callback) {
        Call<Object> response = iLoginUserApi.login(authenticationCredentials);
        response.enqueue(callback);
    }
}
