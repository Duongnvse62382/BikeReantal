package com.duongnv.bikerental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bike implements Serializable {

    @SerializedName("BikeID")
    int bikeID;

    @SerializedName("BikeName")
    String bikeName;

    @SerializedName("Image")
    String image;

    @SerializedName("Price")
    String price;

    @SerializedName("Description")
    String description;

    @SerializedName("StoreName")
    String storeName;

    @SerializedName("TypeName")
    String typeName;

    @SerializedName("Status")
    int status;

    @SerializedName("TypeID")
    int typeID;

    @SerializedName("StoreID")
    int storeID;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
