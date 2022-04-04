package com.example.matvey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.matvey.Service.ApiHandler;
import com.example.matvey.Service.IAPI;
import com.example.matvey.Service.Models.ResponceAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    IAPI api = new ApiHandler().getInstance().getService();

    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        doParse();
    }

    public void doParse(){
        AsyncTask.execute(() -> {
            api.getResponce().enqueue(new Callback<ResponceAll>() {
                @Override
                public void onResponse(Call<ResponceAll> call, Response<ResponceAll> response) {
                    adapter = new CustomAdapter(getApplicationContext(), response.body().getPhotosResponce().getList());
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ResponceAll> call, Throwable t) {

                }
            });
        });
    }
}