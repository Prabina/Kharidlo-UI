package com.ecommerce.kharidlo_ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.User;
import com.ecommerce.kharidlo_ui.viewmodel.RegisterUserViewModel;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistration extends AppCompatActivity {

    private EditText mFullnameView;
    private EditText mPasswordView;
    private EditText mEmailView;
    private EditText mPhoneNumberView;
    private RegisterUserViewModel registerUserViewModel = new RegisterUserViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        mFullnameView = (EditText) findViewById(R.id.fullName);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView = (EditText) findViewById(R.id.emailId);
        mPhoneNumberView = (EditText) findViewById(R.id.phoneNumber);

        mPasswordView.setError(null);
        mFullnameView.setError(null);
        mPhoneNumberView.setError(null);
        mEmailView.setError(null);
    }

    public void onRegisterBtnClick(View view) {

        Boolean isFormValid = validateForm();
        if (!isFormValid) {
            return;
        }

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
                    Toast toast = Toast.makeText(getApplicationContext(), "User Registered Successfully!", Toast.LENGTH_SHORT);
                    toast.show();
                    navigateToLoginScreen();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    System.out.print(t.fillInStackTrace());
                    Toast toast = Toast.makeText(getApplicationContext(), "User Registration failed!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
        catch (Exception ex){
            System.out.print(ex.fillInStackTrace());
        }
    }

    private Boolean validateForm() {
        boolean isValidationFailed = false;

        String fullName = mFullnameView.getText().toString();
        if(fullName == null || fullName.length() == 0) {
            isValidationFailed = true;
            mFullnameView.setError(getString(R.string.error_field_required));
        }

        String password = mPasswordView.getText().toString();
        if(password == null || password.length() < 6) {
            isValidationFailed = true;
            mPasswordView.setError(getString(R.string.error_invalid_password));
        }

        String email = mEmailView.getText().toString();
        if (!isEmailValid(email)) {
            isValidationFailed = true;
            mEmailView.setError(getString(R.string.error_invalid_email));
        }

        String phoneNumber = mPhoneNumberView.getText().toString();
        if (phoneNumber == null || phoneNumber.length() != 10) {
            isValidationFailed = false;
            mPhoneNumberView.setError(getString(R.string.error_phonenumber_invalid));
        }

        return isValidationFailed;
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private void navigateToLoginScreen() {
        Intent intent = new Intent(UserRegistration.this, LoginActivity.class);
        startActivity(intent);
    }
}
