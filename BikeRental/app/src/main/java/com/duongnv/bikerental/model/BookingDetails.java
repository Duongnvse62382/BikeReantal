package com.duongnv.bikerental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingDetails implements Serializable {

    @SerializedName("BikeName")
    private String bikeName;

    @SerializedName("BentalDate")
    private String rentalBike;

    @SerializedName("returnDate")
    private String returnBike;


    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
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

    @Override
    public String toString() {
        return "BookingDetails [BentalDate=" + rentalBike + ", returnDate=" + returnBike +", BikeName=" + bikeName + "]";
    }
}
