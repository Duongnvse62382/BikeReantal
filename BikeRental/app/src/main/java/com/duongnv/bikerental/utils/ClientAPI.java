package com.duongnv.bikerental.utils;

import com.duongnv.bikerental.reponsitory.Service;

public class ClientAPI extends BaseAPI{
    public Service resoService() {
        return this.getService(Service.class, ConfigAPI.BASE_URL);
    }


    // clinet có nhiệm vụ trả về cái serviec tương ứng mà bạn muốn lấy api

    //dựa vào config để biết đường dẫn sau đuôi là gì
}
