package com.example.fuckit2.Service;

import android.speech.SpeechRecognizer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHand {

    private static ApiHand instance;
    private Retrofit retrofit;
    private String baseurl = "https://www.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=415a75f24aa5380555e828f4a6f0f9e4&extras=url_s&per_page=100&page=1&format=json&nojsoncallback=1";

    public ApiHand(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder().baseUrl(baseurl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public ApiHand getInstance(){
        if (instance == null){
            return instance = new ApiHand();
        }
        else {
            return instance;
        }
    }
    public Api getServ(){
        return retrofit.create(Api.class);
    }
}
