package com.example.fuckit2.Service.Mod;

import com.google.gson.annotations.SerializedName;

public class Resp {

    @SerializedName("photos")
    PhotoRes photoRes;


    public PhotoRes getPhotoRes() {
        return photoRes;
    }

    public void setPhotoRes(PhotoRes photoRes) {
        this.photoRes = photoRes;
    }
}
