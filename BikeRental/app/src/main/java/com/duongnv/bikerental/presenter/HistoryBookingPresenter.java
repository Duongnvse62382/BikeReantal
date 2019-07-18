package com.duongnv.bikerental.presenter;

import com.duongnv.bikerental.model.Booking;
import com.duongnv.bikerental.reponsitory.Reponsitory;
import com.duongnv.bikerental.reponsitory.ReponsitoryImplement;
import com.duongnv.bikerental.utils.CallBackData;
import com.duongnv.bikerental.views.HistoryBookingView;

import java.util.List;

public class HistoryBookingPresenter {
    private HistoryBookingView mhistoryBookingView;
    private Reponsitory reponsitory;

    public HistoryBookingPresenter(HistoryBookingView mhistoryBookingView) {
        this.mhistoryBookingView = mhistoryBookingView;
        this.reponsitory = new ReponsitoryImplement();
    }


    public void getHistoryBooking(int id){
        reponsitory.getHistoryBook(id, new CallBackData<List<Booking>>() {
            @Override
            public void onSuccess(List<Booking> list) {
                mhistoryBookingView.getHistorySS(list);
            }

            @Override
            public void onSuccessString(String mess) {

            }

            @Override
            public void onFail(String message) {
                mhistoryBookingView.getFail(message);

            }
        });
    }
}
