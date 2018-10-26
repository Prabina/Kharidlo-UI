package com.ecommerce.kharidlo_ui.viewmodel;

import com.ecommerce.kharidlo_ui.model.User;
import com.ecommerce.kharidlo_ui.remote.IUserRegistrationApi;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterUserViewModel {

    private IUserRegistrationApi iUserRegistrationApi;

    public RegisterUserViewModel(){
        Retrofit retrofit = RetrofitManager.getClient();
        iUserRegistrationApi = retrofit.create(IUserRegistrationApi.class);
    }

    public void register(User user, Callback<Integer> callback) {

        Call<Integer> response = iUserRegistrationApi.register(user);
        response.enqueue(callback);

    }
}
