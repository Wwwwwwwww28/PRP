package com.example.fuckit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuckit2.Service.Mod.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustAdap extends RecyclerView.Adapter<CustAdap.ViewHolter> {

    Context ctx;
    ArrayList<Photo> list;
    LayoutInflater inflater;

    CustAdap(Context ctx, ArrayList<Photo> list){
        this.ctx = ctx;
        this.list = list;
        inflater = (LayoutInflater)  ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public class ViewHolter  extends RecyclerView.ViewHolder{
        final ImageView img;
        public ViewHolter(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
        }
    }
}
