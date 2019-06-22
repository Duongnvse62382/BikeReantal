package com.duongnv.bikerental.reponsitory;

import com.duongnv.bikerental.utils.ConfigAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    @POST(ConfigAPI.Api.GETACCOUNT)
    Call<ResponseBody> registerAccount(
            @Body RequestBody requestBody
    );


    @GET(ConfigAPI.Api.GETACCOUNT)
    Call<ResponseBody> getAccount(

    );
    @PUT(ConfigAPI.Api.GETACCOUNT)
    Call<ResponseBody> updateAccount(
            @Path("username") String username,
            @Body RequestBody requestBody
    );

    //tạo kết nối
}
