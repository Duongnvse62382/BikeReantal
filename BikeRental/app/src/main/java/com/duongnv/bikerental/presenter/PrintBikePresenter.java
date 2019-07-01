package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.GetBikeByStoreView;

import java.util.List;

public class PrintBikePresenter {
    GetBikeByStoreView mgetBikeByStoreView;
    Reponsitory reponsitory;

    public PrintBikePresenter(GetBikeByStoreView mgetBikeByStoreView) {
        this.mgetBikeByStoreView = mgetBikeByStoreView;
        this.reponsitory = new ReponsitoryImplement();
    }

    public void getBikeByStore(){
        reponsitory.getBikeByStore(new CallBackData<List<Bike>>() {
            @Override
            public void onSuccess(List<Bike> list) {
                mgetBikeByStoreView.getBikeSS(list);
            }

            @Override
            public void onSuccessString(String mess) {

            }

            @Override
            public void onFail(String message) {
                mgetBikeByStoreView.getBikeFail(message);

            }
        });
    }
}
