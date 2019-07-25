package com.duongnv.bikerental.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cart implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "bike_id")
    public int bikeid;

    @ColumnInfo(name = "bike_name")
    public String bikename;

    @ColumnInfo(name = "bike_image")
    public String image;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "decription")
    public String decription;

    @ColumnInfo(name = "price")
    public String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getBikeid() {
        return bikeid;
    }

    public void setBikeid(int bikeid) {
        this.bikeid = bikeid;
    }

    public String getBikename() {
        return bikename;
    }

    public void setBikename(String bikename) {
        this.bikename = bikename;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Cart() {
    }
}
