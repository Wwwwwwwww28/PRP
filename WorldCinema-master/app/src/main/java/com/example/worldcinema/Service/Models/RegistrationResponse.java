package com.example.worldcinema.Service.Models;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("token")
    String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
