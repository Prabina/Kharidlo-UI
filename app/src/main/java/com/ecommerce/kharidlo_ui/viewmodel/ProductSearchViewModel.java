package com.ecommerce.kharidlo_ui.viewmodel;
import android.util.Log;

import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.remote.IProductSearchApi;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;
import com.ecommerce.kharidlo_ui.view.fragments.HomeFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductSearchViewModel {

    private IProductSearchApi productSearchApi;
    private HomeFragment activityInstance;
    private String TAG = ProductSearchViewModel.class.getName();

    public ProductSearchViewModel(HomeFragment instance) {
        Retrofit retrofit = RetrofitManager.getClient();
        productSearchApi = retrofit.create(IProductSearchApi.class);
        activityInstance = instance;
    }

    public void search(String searchKey) {
        final Call<List<Product>> response = productSearchApi.search(searchKey);
        response.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d(TAG, "onResponse: "+response.body());
                activityInstance.productsReceived(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t.getMessage());
                activityInstance.productsReceived(null);
            }
        });
    }
}
