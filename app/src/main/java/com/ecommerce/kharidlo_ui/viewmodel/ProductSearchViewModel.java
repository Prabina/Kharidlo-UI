package com.ecommerce.kharidlo_ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.remote.IProductSearchApi;
import com.ecommerce.kharidlo_ui.remote.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductSearchViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products;

    private IProductSearchApi productSearchApi;
    private String TAG = ProductSearchViewModel.class.getName();
    public ProductSearchViewModel() {
        Retrofit retrofit = RetrofitManager.getClient();
        productSearchApi = retrofit.create(IProductSearchApi.class);
        products = new MutableLiveData<>();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void search(String searchKey) {
        final Call<List<Product>> response = productSearchApi.search(searchKey);
        response.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                products.postValue(new ArrayList<Product>());
                Log.d(TAG, "onResponse: " + t);
            }
        });
    }
}
