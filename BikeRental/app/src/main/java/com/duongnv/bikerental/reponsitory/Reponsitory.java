package com.duongnv.bikerental.reponsitory;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Store;
import com.duongnv.bikerental.utils.CallBackData;

import java.util.List;

public interface Reponsitory {
    //    void login(Context context, String username, String password, CallBackData<User> callBackData);
    void getAccount(CallBackData<List<Account>>callBackData);
    void registerAccount(String username, String password, String fullname, String phonenumber, String email, String address, String birtday, int role, CallBackData<List<Account>> callBackData);
    void updateAccount(String username, String password, String fullname, String phonenumber, String email, String address, String birtday, CallBackData<List<Account>> callBackData);
    void getBikeByStore(CallBackData<List<Bike>> callBackData);

    void getStore(CallBackData<List<Store>> callBackData);
}

// chứa các hàm mà các activity cần
//chỉ là nơi kết nối
//Context chỉ thằng nào gọi nó
