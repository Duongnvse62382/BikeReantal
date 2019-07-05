package com.duongnv.bikerental.reponsitory;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Store;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.utils.ClientAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReponsitoryImplement implements Reponsitory{
    @Override
    public void loginAccount(String username, String password, CallBackData<Account> callBackData) {
        ClientAPI clientAPI = new ClientAPI();
        Call<ResponseBody> bodyCall = clientAPI.resoService().loginAccount(username, password);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200 && response.body()!= null){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Type collectionType = new TypeToken<List<Account>>(){}.getType();
                    List<Account> lcs = (List<Account>) new Gson()
                            .fromJson( result , collectionType);

                if(lcs.size()>0){
                    callBackData.onSuccess(lcs.get(0));
                }
                else{
                    callBackData.onFail("fail");
                }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });
    }

    @Override
    public void getAccount(final CallBackData<List<Account>> callBackData) {
        ClientAPI clientAPI = new ClientAPI();
        Call<ResponseBody> bodyCall = clientAPI.resoService().getAccount();
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.code()==200 & response.body()!=null){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Type collectionType = new TypeToken<List<Account>>(){}.getType();
                    List<Account> lcs = (List<Account>) new Gson()
                            .fromJson( result , collectionType);

                    callBackData.onSuccess(lcs);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Username or Password Wrong");

            }
        });

    }

    @Override
    public void registerAccount(String username, String password, String fullname, String phonenumber, String email, String address, String birtday, int role,final CallBackData<List<Account>> callBackData) {

        ClientAPI clientAPI = new ClientAPI();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Username",username);
            jsonObject.put("Password",password);
            jsonObject.put("FullName",fullname);
            jsonObject.put("PhoneNumber",phonenumber);
            jsonObject.put("Email", email);
            jsonObject.put("Address", address);
            jsonObject.put("BirthDay", birtday);
            jsonObject.put("Role", role);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Call<ResponseBody> bodyCall = clientAPI.resoService().registerAccount(requestBody);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200 & response.body() != null){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    callBackData.onSuccessString("Register success!");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("account is exit");

            }
        });

    }

    @Override
    public void updateAccount(int userId,String username, String password, String fullname, String phonenumber, String email, String address, String birtday,int role ,final CallBackData<List<Account>> callBackData) {
        ClientAPI clientAPI = new ClientAPI();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserID", userId);
            jsonObject.put("Username", username);
            jsonObject.put("Password", password);
            jsonObject.put("FullName", fullname);
            jsonObject.put("PhoneNumber", phonenumber);
            jsonObject.put("Email", email);
            jsonObject.put("Address", address);
            jsonObject.put("BirthDay", birtday);
            jsonObject.put("Role", role);
        } catch (JSONException e) {
            e.printStackTrace();
        }
         RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=urf-8"), jsonObject.toString());
        Call<ResponseBody> bodyCall = clientAPI.resoService().updateAccount(username, requestBody);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 & response.body()!=null){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                callBackData.onSuccessString("Update succsess!");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Username not exits!");
            }
        });
    }


    //bike

    @Override
    public void getBikeByStore(final CallBackData<List<Bike>> callBackData) {
        ClientAPI clientAPI = new ClientAPI();
        Call<ResponseBody> bodyCall = clientAPI.resoService().getBikeByStore();
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                if(response.code() == 200 & response.body() != null){
                    try {
                        result =  response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Type collectionType = new TypeToken<List<Bike>>(){}.getType();
                List<Bike> lcs = (List<Bike>) new Gson()
                        .fromJson( result , collectionType);

                callBackData.onSuccess(lcs);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Fail");
            }
        });

    }


    //STORE

    @Override
    public void getStore(CallBackData<List<Store>> callBackData) {
        ClientAPI clientAPI = new ClientAPI();
        Call<ResponseBody> bodyCall = clientAPI.resoService().getStores();
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                if(response.code() == 200 & response.body() != null){
                    try {
                        result =  response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Type collectionType = new TypeToken<List<Store>>(){}.getType();
                List<Store> lcs = (List<Store>) new Gson()
                        .fromJson( result , collectionType);

                callBackData.onSuccess(lcs);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Fail");
            }
        });
    }
}
