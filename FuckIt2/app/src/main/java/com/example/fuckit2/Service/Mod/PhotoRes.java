package com.example.fuckit2.Service.Mod;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotoRes {
    @SerializedName("photo")
    ArrayList<Photo> list = new ArrayList<>();

    public ArrayList<Photo> getList() {
        return list;
    }

    public void setList(ArrayList<Photo> list) {
        this.list = list;
    }
}
