package com.duongnv.bikerental.views;

import com.duongnv.bikerental.model.Booking;

import java.util.List;

public interface HistoryBookingView {
    void getHistorySS(List<Booking> list);
    void getFail(String msg);
}
