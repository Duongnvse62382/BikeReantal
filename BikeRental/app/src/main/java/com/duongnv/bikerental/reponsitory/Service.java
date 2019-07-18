package com.duongnv.bikerental.reponsitory;

import com.duongnv.bikerental.utils.ConfigAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.duongnv.bikerental.utils.ConfigAPI.Api.CREATEACCOUNT;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.LOGINACCOUNT;

import static com.duongnv.bikerental.utils.ConfigAPI.Api.GETBIKEBYSTORE;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.GETSTORES;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.UPDATEACCOUNT;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.BOOKINGBIKE;
import static com.duongnv.bikerental.utils.ConfigAPI.Api.HISTORYBOOK;

public interface Service {
    @POST(CREATEACCOUNT)
    Call<ResponseBody> registerAccount(
            @Body RequestBody requestBody
    );

    @GET(LOGINACCOUNT)
    Call<ResponseBody> loginAccount(
            @Query("username") String username,
            @Query("password") String password
    );


//    @GET(CREATEACCOUNT)
//    Call<ResponseBody> getAccount(
//
//    );

    @PUT(UPDATEACCOUNT)

    Call<ResponseBody> updateAccount(
            @Path("username") String username,
            @Body RequestBody requestBody
    );

    //tạo kết nối

    @GET(GETBIKEBYSTORE)
        Call<ResponseBody> getBikeByStore();


    @GET(GETSTORES)
    Call<ResponseBody> getStores();


    //payment
    @POST(BOOKINGBIKE)
    Call<ResponseBody> bookingPayment(
        @Body RequestBody requestBody
    );

    //history
    @GET(HISTORYBOOK)
    Call<ResponseBody> getHistory(
            @Query("id") int id
    );
}
