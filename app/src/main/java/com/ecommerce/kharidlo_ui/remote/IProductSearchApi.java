package com.ecommerce.kharidlo_ui.remote;

import com.ecommerce.kharidlo_ui.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IProductSearchApi {
    @GET("product")
    Call<List<Product>> search(@Query("searchKey") String searchKey);

    @POST("product/create")
    Call<Product> create(@Body Product product);

}
