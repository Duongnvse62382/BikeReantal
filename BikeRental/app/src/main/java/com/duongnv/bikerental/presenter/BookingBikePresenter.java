package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.BookingBikeView;

public class BookingBikePresenter {
    private BookingBikeView mbookingBikeView;
    private Reponsitory reponsitory;


    public BookingBikePresenter(BookingBikeView mbookingBikeView) {
        this.mbookingBikeView = mbookingBikeView;
        this.reponsitory = new ReponsitoryImplement();
    }


    public void bookingBike(int amount, int slots,int userID ,int bikeId, String rentalBike, String returnBike){
        reponsitory.bookingBike(amount, slots, userID,bikeId, rentalBike, returnBike, new CallBackData() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onSuccessString(String mess) {

                mbookingBikeView.InsertSS(mess);
            }

            @Override
            public void onFail(String message) {
                mbookingBikeView.InsertFail(message);

            }
        });
    }



}
