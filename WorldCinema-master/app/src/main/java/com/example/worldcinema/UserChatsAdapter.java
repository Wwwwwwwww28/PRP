package com.example.worldcinema;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.worldcinema.Service.ApiHandler;
import com.example.worldcinema.Service.IApi;
import com.example.worldcinema.Service.Models.ChatMessagesResponse;
import com.example.worldcinema.Service.Models.UserChatsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserChatsAdapter extends BaseAdapter {

    IApi iApi = new ApiHandler().getService();
    private Context ctx;
    private LayoutInflater inflater;
    private List<UserChatsResponse> list = new ArrayList<>();
    private String token = "835381";

    UserChatsAdapter(Context context, List<UserChatsResponse> list){
        this.ctx = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserChatsResponse getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if(v == null){
            v = inflater.inflate(R.layout.discus_list_item, viewGroup, false);
        }
        UserChatsResponse userChat = list.get(i);
        ((TextView) v.findViewById(R.id.discusionName)).setText(userChat.getName());

        View finalV = v;
        AsyncTask.execute(() -> {
            iApi.getChatMessages(token, String.valueOf(userChat.getChatId())).enqueue(new Callback<List<ChatMessagesResponse>>() {
                @Override
                public void onResponse(Call<List<ChatMessagesResponse>> call, Response<List<ChatMessagesResponse>> response) {
                    if(response.isSuccessful()){
                        int idLastMessage = response.body().size();
                        ((TextView) finalV.findViewById(R.id.lastMessage)).setText(response.body().get(idLastMessage-1).getText());

                    }
                }

                @Override
                public void onFailure(Call<List<ChatMessagesResponse>> call, Throwable t) {

                }

            });
        });

        v=finalV;


        return v;
    }

}
