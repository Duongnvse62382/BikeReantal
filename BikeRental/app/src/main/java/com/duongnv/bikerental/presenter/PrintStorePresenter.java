package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Store;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.GetStoreView;

import java.util.List;

public class PrintStorePresenter {
    GetStoreView mgetStoreView;
    Reponsitory reponsitory;

    public PrintStorePresenter(GetStoreView mgetStoreView) {
        this.mgetStoreView = mgetStoreView;
        this.reponsitory = new ReponsitoryImplement();
    }

    public void getStore(){
        reponsitory.getStore(new CallBackData<List<Store>>() {
            @Override
            public void onSuccess(List<Store> stores) {
                mgetStoreView.getBikeSS(stores);
            }

            @Override
            public void onSuccessString(String mess) {

            }

            @Override
            public void onFail(String message) {
                mgetStoreView.getBikeFail(message);

            }
        });
    }
}
