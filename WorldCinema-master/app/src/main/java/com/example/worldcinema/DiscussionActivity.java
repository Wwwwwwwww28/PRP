package com.example.worldcinema;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Service.ApiHandler;
import com.example.worldcinema.Service.IApi;
import com.example.worldcinema.Service.Models.UserChatsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionActivity extends AppCompatActivity {
    UserChatsAdapter adapter;
    IApi IApi = new ApiHandler().getService();
    private static String token = "0";

    private String movieID;
    public SharedPreferences sp;
    List<UserChatsResponse> chatsResponses = new ArrayList<>();
    ListView lv;
    List<UserChatsResponse> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        lv = findViewById(R.id.listViewChats);

        sp = getApplicationContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        try{
            token = sp.getString("token", "");
            Bundle bundle = getIntent().getExtras();
            movieID = bundle.get("movieID").toString();
            Toast.makeText(getApplicationContext(), "" + movieID, Toast.LENGTH_LONG);
            getListChats();
        }catch(Exception e){
            getListUserChat();
        }


        ListView listView;
        listView = findViewById(R.id.listViewChats);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                    intent.putExtra("chatID", list.get(i).getChatId());
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(DiscussionActivity.this, "Ошибка возможно, список не чатов не загружен: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getListUserChat() {
        AsyncTask.execute(()->{
            Message msg = handler.obtainMessage();
            Bundle bundle = new Bundle();
            IApi.getUserChats(token).enqueue(new Callback<List<UserChatsResponse>>() {//загружаем список чатов
                @Override
                public void onResponse(Call<List<UserChatsResponse>> call, Response<List<UserChatsResponse>> response) {
                    if(response.isSuccessful()){
                        chatsResponses = response.body();
                        try {
                            String dateString = response.body().get(0).getName();
                            bundle.putString("Key", dateString);//необходимо для синхронизации сообщений
                            list = response.body();
                        }catch (Exception e){
                            Toast.makeText(DiscussionActivity.this, "Ошибка нет чатов", Toast.LENGTH_SHORT).show();
                        }
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }else if(response.code() == 201){


                    }
                    else{
                        Toast.makeText(DiscussionActivity.this, "Код ошибки " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<UserChatsResponse>> call, Throwable t) {
                    Toast.makeText(DiscussionActivity.this, "Косяк с интернетом" , Toast.LENGTH_SHORT).show();
                }


            });
        });
    }

    public void getListChats() {

       AsyncTask.execute(()->{
           Message msg = handler.obtainMessage();
           Bundle bundle = new Bundle();
            IApi.getUserChats(token, movieID).enqueue(new Callback<List<UserChatsResponse>>() {
                @Override
                public void onResponse(Call<List<UserChatsResponse>> call, Response<List<UserChatsResponse>> response) {
                    if(response.isSuccessful()){
                        chatsResponses = response.body();
                        try {
                            String dateString = response.body().get(0).getName();
                            bundle.putString("Key", dateString);
                            list = response.body();
                        }catch (Exception e){
                            Toast.makeText(DiscussionActivity.this, "Ошибка нет чатов", Toast.LENGTH_SHORT).show();
                        }
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }else if(response.code() == 201){


                    }
                    else{
                        Toast.makeText(DiscussionActivity.this, "Код ошибки " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<UserChatsResponse>> call, Throwable t) {
                    Toast.makeText(DiscussionActivity.this, "Косяк с интернетом" , Toast.LENGTH_SHORT).show();
                }


            });
        });

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String date = bundle.getString("Key");
            adapter = new UserChatsAdapter(getApplicationContext(), chatsResponses);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();//обновляем список сообщений
        }
    };
}