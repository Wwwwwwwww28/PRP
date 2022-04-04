package com.example.matvey.Service.Models;

import com.google.gson.annotations.SerializedName;

public class ResponceAll {
    @SerializedName("photos")
    PhotosResponce photosResponce;

    public PhotosResponce getPhotosResponce() {
        return photosResponce;
    }

    public void setPhotosResponce(PhotosResponce photosResponce) {
        this.photosResponce = photosResponce;
    }
}
