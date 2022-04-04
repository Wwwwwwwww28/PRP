package com.example.fuckit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fuckit2.Service.Api;
import com.example.fuckit2.Service.ApiHand;
import com.example.fuckit2.Service.Mod.Resp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Api api = new ApiHand().getInstance().getServ();

    RecyclerView recyclerView;
    CustAdap adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        doParse();
    }

    public void doParse(){
        AsyncTask.execute(() -> {
            api.getResp().enqueue(new Callback<Resp>() {
                @Override
                public void onResponse(Call<Resp> call, Response<Resp> response) {
                    adap = new CustAdap(getApplicationContext(), response.body().getPhotoRes().getList());
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                    recyclerView.setAdapter(adap);
                }

                @Override
                public void onFailure(Call<Resp> call, Throwable t) {
                }
            });
        });
    }
}