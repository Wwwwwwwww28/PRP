package com.example.matvey;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matvey.Service.Models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolter> {

    Context ctx;
    ArrayList<Photo> list;
    LayoutInflater inflater;

    CustomAdapter(Context ctx, ArrayList<Photo> list){
        this.ctx = ctx;
        this.list = list;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.element, parent, false);
        return new ViewHolter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolter holder, int position) {
        Photo photo = list.get(position);
        new Picasso.Builder(ctx).build().load(photo.getUrl_s()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolter extends RecyclerView.ViewHolder {
        final ImageView img;
        public ViewHolter(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
