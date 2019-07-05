package com.duongnv.bikerental.reponsitory;

import com.duongnv.bikerental.utils.ConfigAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.duongnv.bikerental.utils.ConfigAPI.Api.GETACCOUNT;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.GETBIKEBYSTORE;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.GETSTORES;

public interface Service {
    @POST(GETACCOUNT)
    Call<ResponseBody> registerAccount(
            @Body RequestBody requestBody
    );


    @GET(GETACCOUNT)
    Call<ResponseBody> getAccount(

    );

    @PUT(GETACCOUNT)
    Call<ResponseBody> updateAccount(
            @Path("username") String username,
            @Body RequestBody requestBody
    );

    //tạo kết nối

    @GET(GETBIKEBYSTORE)
        Call<ResponseBody> getBikeByStore();


    @GET(GETSTORES)
    Call<ResponseBody> getStores();
}
