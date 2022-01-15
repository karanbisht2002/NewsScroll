package com.welcomekaran.newsscroll;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolderclass> {

  private ArrayList<Articles>  articlesArrayList;
  private Context context;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolderclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycleview,parent,false);
        return new NewsRVAdapter.ViewHolderclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderclass holder, int position) {
        Articles articles=articlesArrayList.get(position);
        Picasso.get().load(articles.getUrlToImage()).into(holder.image);
        holder.title.setText(articles.getTitle());
        holder.subtitle.setText(articles.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewDetailsActivity.class);
                intent.putExtra("title",articles.getTitle());
                intent.putExtra("desc",articles.getDescription());
                intent.putExtra("contant",articles.getContent());
                intent.putExtra("url",articles.getUrl());
                intent.putExtra("image",articles.getUrlToImage());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolderclass extends RecyclerView.ViewHolder
    {
             private TextView title,subtitle;
             private ImageView image;
        public ViewHolderclass(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.news_headline);
            subtitle=itemView.findViewById(R.id.news_desc);
            image=itemView.findViewById(R.id.news_image_view);

        }
    }
}
