package com.example.worldcinema;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.Service.Models.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context ctx;
    LayoutInflater inflater;
    ArrayList<MovieResponse> list;
    OnItemClickListener mItemClickListener;

    CustomAdapter(Context context, ArrayList<MovieResponse> movies){
        this.ctx = context;
        this.list = movies;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_for_list, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.title);
            iv = itemView.findViewById(R.id.image12);

        }
        public void bind(int position){
            label.setText(list.get(position).getName());
            Picasso.with(ctx)
                    .load("http://cinema.areas.su/up/images/" + list.get(position).getPoster())
                    .into(iv);//загружаем картинку и отображаем ее в imageview
            itemView.setOnClickListener(new View.OnClickListener() {//привязываем обработчик
                @Override
                public void onClick(View view) {
                    String movieID = String.valueOf(list.get(position).getMovieId());
                    Intent intent = new Intent(ctx, DiscussionActivity.class);
                    intent.putExtra("movieID", movieID);//передаем в активити id фильма
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = (OnItemClickListener) mItemClickListener;
    }
}
