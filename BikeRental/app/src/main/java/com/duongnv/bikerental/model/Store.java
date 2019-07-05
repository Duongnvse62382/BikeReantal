package com.duongnv.bikerental.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

public class Store implements ClusterItem, Serializable {

    @SerializedName("StoreName")
    private final String nameStore;
    @SerializedName("StorePhoneNo")
    private  String storePhone;

    @SerializedName("StoreAddress")
    private String storeAddress;



    private final LatLng latLng;

    public Store(String nameStore, LatLng latLng) {
        this.nameStore = nameStore;
        this.latLng = latLng;
    }

    public String getNameStore() {
        return nameStore;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
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


