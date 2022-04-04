package com.example.matvey.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    private static ApiHandler instance;
    private Retrofit retrofit;
    private String baseurl="https://www.flickr.com/services/rest/";

    public ApiHandler() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder().baseUrl(baseurl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public ApiHandler getInstance(){
        if (instance == null) {
            return instance = new ApiHandler();
        }
        else{
            return instance;
        }
    }
    public IAPI getService(){
        return retrofit.create(IAPI.class);
    }
}
