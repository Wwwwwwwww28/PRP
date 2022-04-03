package com.example.worldcinema.Service.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponce {
    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
