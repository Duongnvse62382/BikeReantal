package com.duongnv.bikerental.views;

import com.duongnv.bikerental.model.Store;

import java.util.List;

public interface GetStoreView {
    void getBikeSS(List<Store> list);
    void getBikeFail(String message);
}
