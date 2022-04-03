package com.example.worldcinema;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Service.ApiHandler;
import com.example.worldcinema.Service.IApi;
import com.example.worldcinema.Service.Models.ChatMessagesResponse;
import com.example.worldcinema.Service.Models.SendMessageBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {


    ListView listView;
    IApi api = ApiHandler.getInstance().getService();
    SharedPreferences sp;
    String token;
    String chatID;
    ChatAdapter adapter;
    List<ChatMessagesResponse> list;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listView = findViewById(R.id.listChat);

        token = getApplicationContext().getSharedPreferences("settings", Context.MODE_PRIVATE).getString("token", "");
        Bundle arg = getIntent().getExtras();
        chatID = arg.get("chatID").toString();

        et = findViewById(R.id.messageText);

        loadChat();
        ImageView img = findViewById(R.id.sendButton);
        img.setOnClickListener(view -> {
            sendMessage();
        });
    }

    public void sendMessage(){
        String message = et.getText().toString();
        SendMessageBody body = new SendMessageBody();//генерируем тело запроса
        body.setText(message);
        api.sendMessage(token, chatID, body).enqueue(new Callback<ChatMessagesResponse>() {//отправляем сообщение
            @Override
            public void onResponse(Call<ChatMessagesResponse> call, Response<ChatMessagesResponse> response) {
               if(response.isSuccessful()){
                   loadChat();//обновляем чат
                   et.setText("");
               }
            }

            @Override
            public void onFailure(Call<ChatMessagesResponse> call, Throwable t) {

            }
        });
    }

    public void loadChat(){
        api.getChatMessages(token, chatID).enqueue(new Callback<List<ChatMessagesResponse>>() {//получаем список сообщений чата
            @Override
            public void onResponse(Call<List<ChatMessagesResponse>> call, Response<List<ChatMessagesResponse>> response) {
                List<ChatMessagesResponse> list1 = response.body();
                ChatAdapter adapt = new ChatAdapter(getApplication(), list1);
                listView = findViewById(R.id.listChat);
                listView.setAdapter(adapt); //загружаем адаптер в список
            }

            @Override
            public void onFailure(Call<List<ChatMessagesResponse>> call, Throwable t) {

            }
        });
    }
}