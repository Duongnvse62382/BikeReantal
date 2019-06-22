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

    public void loginAccount(){
        reponsitory.getAccount(new CallBackData<List<Account>>() {
            @Override
            public void onSuccess(List<Account> list) {
                loginAccountView.loginSS(list);

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

