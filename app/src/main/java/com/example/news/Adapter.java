package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.UserHolder> {

    Context context;
    ArrayList<Model> models;

    public Adapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdesign, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        String title_news = models.get(position).getTitle();
        String news_img = models.get(position).getUrlimg();
        holder.tv_title.setText(title_news);
        Picasso picasso = null;
        Picasso.with(context).load(news_img).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new Gson();
                String ss = gson.toJson(models.get(position));

                Intent intent = new Intent(context, Detail.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key", models.get(position).getId());
                intent.putExtra("modelArrayList", ss);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView img;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            img = itemView.findViewById(R.id.img);

        }
    }
}
