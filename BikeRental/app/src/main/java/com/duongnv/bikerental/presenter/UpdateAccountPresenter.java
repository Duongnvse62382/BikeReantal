package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.UpdateAccountView;

import java.util.List;

public class UpdateAccountPresenter {
    UpdateAccountView mupdateAccountView;
    Reponsitory reponsitory;

    public UpdateAccountPresenter(UpdateAccountView mupdateAccountView) {
        this.mupdateAccountView = mupdateAccountView;
        this.reponsitory = new ReponsitoryImplement();
    }

    public void updateAccount(int userId, String username, String password, String fullname, String phonenumber, String email, String address, String birtday, int role){
        reponsitory.updateAccount(userId, username, password, fullname, phonenumber, email, address, birtday, 1, new CallBackData<List<Account>>() {
            @Override
            public void onSuccess(List<Account> list) {

            }

            @Override
            public void onSuccessString(String mess) {
                 mupdateAccountView.updateSS(mess);
            }

            @Override
            public void onFail(String message) {
                mupdateAccountView.updateFail(message);

            }
        });

    }
}
