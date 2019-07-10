package com.duongnv.bikerental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Booking implements Serializable {

    @SerializedName("Amount")
    private float amount;

    @SerializedName("Slots")
    private int slots;

    public float getAmount() {
        return amount;
    }

    @SerializedName("BookingDetails")
    private BookingDetails bookingDetails;


    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}
