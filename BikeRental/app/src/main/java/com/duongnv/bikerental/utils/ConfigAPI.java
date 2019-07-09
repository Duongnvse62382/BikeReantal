package com.duongnv.bikerental.utils;

public class ConfigAPI {
    public static final String BASE_URL = "http://baonc.azurewebsites.net/";

    public interface Api {
        String LOGINACCOUNT = "Accounts/id";
        String GETBIKEBYSTORE = "Bikes";
        String GETSTORES = "Stores";
        String UPDATEACCOUNT = "Accounts";
        String CREATEACCOUNT = "Accounts";
        String BOOKINGBIKE = "Bookings";
    }

    //đường dẫn API
}
