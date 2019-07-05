package com.duongnv.bikerental.views;

import com.duongnv.bikerental.model.Account;

import java.util.List;

public interface LoginAccountView {
    void loginSS(Account account);
    void loginFail(String message);
}
