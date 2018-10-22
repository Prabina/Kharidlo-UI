package com.ecommerce.kharidlo_ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.User;
import com.ecommerce.kharidlo_ui.modelview.RegisterUserViewModel;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRegistration extends AppCompatActivity {

    RegisterUserViewModel registerUserViewModel = new RegisterUserViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }

    public void onRegisterBtnClick(View view) {
        EditText fullNameField = (EditText)findViewById(R.id.fullName);
        String fullName = fullNameField.getText().toString();

        EditText emailIdField = (EditText)findViewById(R.id.emailId);
        String emainId = emailIdField.getText().toString();

        EditText passwordField = (EditText)findViewById(R.id.password);
        String password = passwordField.getText().toString();

        EditText phoneNumberField = (EditText)findViewById(R.id.phoneNumber);
        String phoneNumber = phoneNumberField.getText().toString();

        User user = new User(fullName, emainId, password, phoneNumber);

        try{
            registerUserViewModel.register(user, new Callback<Integer>() {
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
        catch (Exception ex){
            System.out.print(ex.fillInStackTrace());
        }


    }
}
