package com.duongnv.bikerental.reponsitory;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.utils.CallBackData;

import java.util.List;

public interface Reponsitory {
    //    void login(Context context, String username, String password, CallBackData<User> callBackData);
    void getAccount(CallBackData<List<Account>>callBackData);
    void registerAccount(String username, String password, String fullname, String phonenumber, String email, String address, String birtday, int role, CallBackData<List<Account>> callBackData);
}

// chứa các hàm mà các activity cần
//chỉ là nơi kết nối
//Context chỉ thằng nào gọi nó
