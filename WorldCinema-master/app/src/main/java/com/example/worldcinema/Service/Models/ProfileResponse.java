package com.example.worldcinema.Service.Models;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("userID")
    int userID;
    @SerializedName("firstName")
    String firstname;
    @SerializedName("lastName")
    String lastname;
    @SerializedName("email")
    String email;
    @SerializedName("avatar")
    String avatar;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
