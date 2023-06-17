package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>   {

    Context context;
    ArrayList<NewsData> newsDataList;


    public NewsAdapter(Context context, ArrayList<NewsData> newsDataList){
        this.context = context;
        this.newsDataList = newsDataList;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {

        NewsData newsData = newsDataList.get(position);
        holder.title.setText(newsData.title);
        holder.description.setText(newsData.description);
        holder.price.setText(newsData.price+ " ₽");

    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.header_news);
            description = itemView.findViewById(R.id.description_news);
            price = itemView.findViewById(R.id.price_news);

        }
    }
}
