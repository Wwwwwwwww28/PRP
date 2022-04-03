package com.example.worldcinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.worldcinema.Service.Models.ChatMessagesResponse;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context ctx;
    private List<ChatMessagesResponse> list;
    private LayoutInflater inflaterLeft;
    private LayoutInflater inflaterRight;

    ChatAdapter (Context ctx, List<ChatMessagesResponse> list){
        this.ctx = ctx;
        this.list = list;
        this.inflaterLeft = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.inflaterRight = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ChatMessagesResponse getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vLeft = view;
        if(vLeft == null){
            vLeft = inflaterLeft.inflate(R.layout.left_chat_dialog, viewGroup, false);
        }

        ((TextView)vLeft.findViewById(R.id.message)).setText(list.get(i).getText());

        return vLeft;
    }
}
