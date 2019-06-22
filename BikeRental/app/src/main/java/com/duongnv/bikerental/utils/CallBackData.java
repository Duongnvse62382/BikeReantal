package com.duongnv.bikerental.utils;

public interface CallBackData <T> {
    void onSuccess(T t);
    void onSuccessString (String mess);
    void onFail(String message);
}

//dữ liệu ko xác định
//hứng dữ liệu từ phía reponsitory
