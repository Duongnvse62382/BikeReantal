package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.UpdateStatusBikeView;

public class UpdateStatusBikePresenter {
    UpdateStatusBikeView mUpdateStatusBikeView;
    Reponsitory reponsitory;

    public UpdateStatusBikePresenter(UpdateStatusBikeView mUpdateStatusBikeView) {
        this.mUpdateStatusBikeView = mUpdateStatusBikeView;
        this.reponsitory = new ReponsitoryImplement();
    }
     public void updateStatusBike(int bikeID, String bikeName, String image, String decription, String price, int status, int typeID, int storeID){
        reponsitory.updateStatusBike(bikeID, bikeName, image, decription, price, status, typeID, storeID, new CallBackData<Bike>() {
            @Override
            public void onSuccess(Bike bike) {

            }

            @Override
            public void onSuccessString(String mess) {
               mUpdateStatusBikeView.updateSS(mess);
            }

            @Override
            public void onFail(String message) {
               mUpdateStatusBikeView.updateFail(message);
            }
        });
     }
}
