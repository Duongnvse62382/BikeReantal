package com.duongnv.bikerental.model;

public class ResponseResult<T> {
    private DataReponse<T> data;

    public ResponseResult() {
    }


    public DataReponse<T> getData() {
        return data;
    }

    public void setData(DataReponse<T> data) {
        this.data = data;
    }
}
