package com.example.worldcinema.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    String baseurl = "http://cinema.areas.su/"; //базовая ссылка на api
    private Retrofit retrofit;//создаем объект ретрофит
    private static ApiHandler mInstance;//обьявляем статический обьект



    public ApiHandler(){//конструктор
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();//настраиваем степень логирования интернет запросов
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor);

        retrofit = new Retrofit.Builder() //конфигурируем службы апи
                .baseUrl(baseurl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }
    public static  ApiHandler getInstance(){
        if(mInstance == null){
            mInstance = new ApiHandler();
        }
        return mInstance;
    }
    public IApi getService(){
        return retrofit.create(IApi.class);
    }
}
