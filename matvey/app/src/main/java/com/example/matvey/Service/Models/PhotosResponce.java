package com.example.matvey.Service.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotosResponce {
    @SerializedName("photo")
    ArrayList<Photo> list = new ArrayList<>();

    public ArrayList<Photo> getList() {
        return list;
    }

    public void setList(ArrayList<Photo> list) {
        this.list = list;
    }
}
