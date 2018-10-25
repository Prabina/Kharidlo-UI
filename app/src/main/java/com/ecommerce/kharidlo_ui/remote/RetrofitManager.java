package com.ecommerce.kharidlo_ui.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit retrofit = null;
    private static final String baseUrl = "https://lazy-owl-78.localtunnel.me";
//    private static final String baseUrl = "http://10.132.22.219:8085";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
