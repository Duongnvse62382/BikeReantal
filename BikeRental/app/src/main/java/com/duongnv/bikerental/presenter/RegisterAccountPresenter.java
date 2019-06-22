package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.InsertAccountView;

import java.util.Date;
import java.util.List;

public class RegisterAccountPresenter {
    InsertAccountView insertAccountView;
    Reponsitory reponsitory;

    public RegisterAccountPresenter(InsertAccountView insertAccountView) {
        this.insertAccountView = insertAccountView;
        this.reponsitory =  new ReponsitoryImplement();
    }
    public void insertAcocunt(String username, String password, String fullname, String phonenumber, String email, String address, String birtday, int role){
        reponsitory.registerAccount(username, password, fullname, phonenumber, email, address, birtday, 1, new CallBackData<List<Account>>() {
            @Override
            public void onSuccess(List<Account> accounts) {

            }

            @Override
            public void onSuccessString(String mess) {
                 insertAccountView.InsertSS(mess);
            }

            @Override
            public void onFail(String message) {
                  insertAccountView.InsertFail(message);
            }
        });
    }
}
