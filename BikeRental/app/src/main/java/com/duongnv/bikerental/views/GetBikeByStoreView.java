package com.duongnv.bikerental.views;

import com.duongnv.bikerental.model.Bike;

import java.util.List;

public interface GetBikeByStoreView {
    void getBikeSS(List<Bike> list);
    void getBikeFail(String message);
}
