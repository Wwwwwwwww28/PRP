package com.example.fuckit2.Service.Mod;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("url_s")
    String url_s;

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }
}
