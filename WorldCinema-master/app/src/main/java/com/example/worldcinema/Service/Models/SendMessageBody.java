package com.example.worldcinema.Service.Models;

import com.google.gson.annotations.SerializedName;

public class SendMessageBody {
    @SerializedName("text")
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
