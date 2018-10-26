package com.ecommerce.kharidlo_ui.viewmodel;

import com.ecommerce.kharidlo_ui.model.AuthenticationCredentials;
import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.remote.ILoginUserApi;
import com.ecommerce.kharidlo_ui.remote.IProductSearchApi;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CreateProductViewModel {

    private final IProductSearchApi productSreachApi;

    public CreateProductViewModel(){
        Retrofit retrofit = RetrofitManager.getClient();
        productSreachApi = retrofit.create(IProductSearchApi.class);
    }

    public void create(Product product, retrofit2.Callback<Product> callback) {
        Call<Product> response = productSreachApi.create(product);
        response.enqueue(callback);
    }

}
