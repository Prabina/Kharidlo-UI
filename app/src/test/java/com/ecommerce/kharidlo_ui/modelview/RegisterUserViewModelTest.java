package com.ecommerce.kharidlo_ui.modelview;

import com.ecommerce.kharidlo_ui.model.User;
import com.ecommerce.kharidlo_ui.viewmodel.RegisterUserViewModel;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterUserViewModelTest  {
    @Test
    public void shouldCallCreateUserAPIIfValidInputsPassed() throws InterruptedException {
        User user = new User("Test User", "a@b.com", "pass", "12345");
        RegisterUserViewModel registerUserViewModel = new RegisterUserViewModel();
        registerUserViewModel.register(user, new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                System.out.print(response.message());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                System.out.print(t.fillInStackTrace());
            }
        });

        
    }


}
