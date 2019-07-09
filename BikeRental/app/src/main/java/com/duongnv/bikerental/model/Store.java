package com.duongnv.bikerental.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;
import java.util.List;

public class Store implements ClusterItem, Serializable {


    @SerializedName("StoreID")
    private int storeID;


    @SerializedName("StoreName")
    private final String nameStore;

    @SerializedName("StorePhoneNo")
    private  int storePhone;

    @SerializedName("StoreAddress")
    private String storeAddress;

    @SerializedName("StoreEmail")
    private String storeEmail;

    @SerializedName("UserID")
    private int userID;

    @SerializedName("Bike")
    private List<Bike> Bike;



    private final LatLng latLng;

    public Store(String nameStore, LatLng latLng) {
        this.nameStore = nameStore;
        this.latLng = latLng;
    }

    public String getNameStore() {
        return nameStore;
    }


    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }




    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }


    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(int storePhone) {
        this.storePhone = storePhone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<com.duongnv.bikerental.model.Bike> getBike() {
        return Bike;
    }

    public void setBike(List<com.duongnv.bikerental.model.Bike> bike) {
        Bike = bike;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return nameStore;
    }

    @Override
    public String getSnippet() {
        return "";
    }
}


