package com.duongnv.bikerental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {

    @SerializedName("Username")
    String username;

    @SerializedName("Password")
    String password;

    @SerializedName("FullName")
    String fullname;

    @SerializedName("PhoneNumber")
    String phonnumber;

    @SerializedName("Email")
    String email;

    @SerializedName("Address")
    String address;

    @SerializedName("BirthDay")
    String brithdate;

    @SerializedName("Role")
    int role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonnumber() {
        return phonnumber;
    }

    public void setPhonnumber(String phonnumber) {
        this.phonnumber = phonnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrithdate() {
        return brithdate;
    }

    public void setBrithdate(String brithdate) {
        this.brithdate = brithdate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
