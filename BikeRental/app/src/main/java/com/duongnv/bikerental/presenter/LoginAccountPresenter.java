package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.LoginAccountView;

import java.util.List;

public class LoginAccountPresenter {
    LoginAccountView loginAccountView;
    Reponsitory reponsitory;

    public LoginAccountPresenter(LoginAccountView loginAccountView) {
        this.loginAccountView = loginAccountView;
        this.reponsitory = new ReponsitoryImplement();
    }

    public void loginAccount(String username, String password){
        reponsitory.loginAccount(username, password, new CallBackData<Account>() {
            @Override
            public void onSuccess(Account account) {
                loginAccountView.loginSS(account);
            }

            @Override
            public void onSuccessString(String mess) {

            }

            @Override
            public void onFail(String message) {
                loginAccountView.loginFail(message);

            }
        });
    }
}

