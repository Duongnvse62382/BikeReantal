package com.duongnv.bikerental.views;

import com.duongnv.bikerental.model.Account;

import java.util.List;

public interface LoginAccountView {
    void loginSS(List<Account> list);
    void loginFail(String message);
}
