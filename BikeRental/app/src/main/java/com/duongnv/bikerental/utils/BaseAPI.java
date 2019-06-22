package com.duongnv.bikerental.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseAPI {
    private Retrofit retrofitAdapter;
    public Retrofit getRetrofitAdapter() {
        return retrofitAdapter;
    }
    public <T> T getService(Class<T> tClass, String url) {
        if (getRetrofitAdapter() == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(80, TimeUnit.SECONDS).build();
            retrofitAdapter = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return getRetrofitAdapter().create(tClass);
    }
    //// tạo đường dẫn client chung kết nối với api
}
