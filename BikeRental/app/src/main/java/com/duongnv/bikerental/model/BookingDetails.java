package com.duongnv.bikerental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingDetails implements Serializable {

    @SerializedName("BikeID")
    private int bikeID;

    @SerializedName("BentalDate")
    private String rentalBike;

    @SerializedName("returnDate")
    private String returnBike;


    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getRentalBike() {
        return rentalBike;
    }

    public void setRentalBike(String rentalBike) {
        this.rentalBike = rentalBike;
    }

    public String getReturnBike() {
        return returnBike;
    }

    public void setReturnBike(String returnBike) {
        this.returnBike = returnBike;
    }
}
