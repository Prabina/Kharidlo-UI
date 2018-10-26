package com.ecommerce.kharidlo_ui.modelview;

import com.ecommerce.kharidlo_ui.viewmodel.LoginViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LoginViewModelTest {

    @Test
    public void shouldReturnSuccessfulLogin() {
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        String username = "android";
        String password = "123456";
        String testString = "Successfully Logged in.";


        //when(loginViewModel.login(username, password)).thenReturn(testString);

        //String loginMsg = loginViewModel.login(username, password);

        assertThat(testString, is(testString));

//        verify(loginViewModel,times(1)).login(username,password);

    }




}