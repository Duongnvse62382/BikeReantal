package com.duongnv.bikerental.model;

public class DataReponse<T> {
    private T data;

    public DataReponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
