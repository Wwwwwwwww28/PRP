package com.example.worldcinema.Service.Models;

import com.google.gson.annotations.SerializedName;

public class UserChatsResponse {
    @SerializedName("chatId")
    int chatId;
    @SerializedName("name")
    String name;

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
