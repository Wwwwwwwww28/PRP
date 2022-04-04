package com.example.matvey.Service;

import com.example.matvey.Service.Models.Photo;
import com.example.matvey.Service.Models.ResponceAll;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPI {
    @GET("https://www.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=415a75f24aa5380555e828f4a6f0f9e4&extras=url_s&per_page=100&page=1&format=json&nojsoncallback=1")
    Call<ResponceAll> getResponce();
}
