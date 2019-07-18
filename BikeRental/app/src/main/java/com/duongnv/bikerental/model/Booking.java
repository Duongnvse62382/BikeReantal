package com.duongnv.bikerental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable {

    @SerializedName("BookID")
    private int bookID;
    @SerializedName("Amount")
    private int amount;

    @SerializedName("Slots")
    private int slots;

    @SerializedName("UserID")
    private int userID;

    @SerializedName("BookingDetails")
    private List<BookingDetails> bookingDetails = new ArrayList<BookingDetails>();






    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<BookingDetails> getBookingDetails() {
        return bookingDetails;

    }

    public void setBookingDetails(List<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
