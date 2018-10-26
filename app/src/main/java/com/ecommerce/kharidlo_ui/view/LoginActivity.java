package com.ecommerce.kharidlo_ui.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.AuthenticationCredentials;
import com.ecommerce.kharidlo_ui.modelview.LoginViewModel;
import com.ecommerce.kharidlo_ui.utils.SharedPreferenceUtil;
import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button signUpButton;
    private LoginViewModel loginViewModel = new LoginViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        signUpButton = (Button) findViewById(R.id.sign_up_button);

        Button mEmailLogInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailLogInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button mEmailSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mEmailSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUpBtnClick(v);
            }
        });
    }


    private void attemptLogin() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        //TODO: Validate email and password
        loginUser(email, password);
    }

    private static final String TAG = "LoginActivity";
    private void loginUser(String email, String password) {
        AuthenticationCredentials authenticationCredentials = new AuthenticationCredentials(email, password);
        try{
            loginViewModel.login(authenticationCredentials, new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Log.d(TAG, "onResponse: "+response.message());
//                    System.out.print(response.message());
                    if (response.code() == 403 || response.code() == 404) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Oops! Invalid username or password", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (response.code() == 202) {
                        String token = (String) ((LinkedTreeMap) response.body()).get("token");
                        String userRole = (String) ((LinkedTreeMap) response.body()).get("role");
                        SharedPreferenceUtil.setLoggedIn(true);
                        SharedPreferenceUtil.setTOKEN(token);
                        SharedPreferenceUtil.setUserRole(userRole);
                        navigateToHomeScreen();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    System.out.print(t.fillInStackTrace());
                    Toast toast = Toast.makeText(getApplicationContext(), "Oops! Login failed :(", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
        catch (Exception ex){
            System.out.print(ex.fillInStackTrace());
        }
    }

    private void navigateToHomeScreen() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 6;
    }

    private void hideKeyBoard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    public void onSignUpBtnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, UserRegistration.class);
        startActivity(intent);
    }

}

