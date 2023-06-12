package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnalyzesAdapter extends RecyclerView.Adapter<AnalyzesAdapter.MyViewHolder> {

    Context context;
    ArrayList<AnalyzesData> analyzesDataList;

    public AnalyzesAdapter(Context context, ArrayList<AnalyzesData> analyzesDataList){
        this.context = context;
        this.analyzesDataList = analyzesDataList;
    }

    @NonNull
    @Override
    public AnalyzesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_analyze, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalyzesAdapter.MyViewHolder holder, int position) {
        AnalyzesData analyzesData = analyzesDataList.get(position);
        holder.title.setText(analyzesData.title);
        holder.day.setText(analyzesData.day);
        holder.price.setText(analyzesData.price);
    }

    @Override
    public int getItemCount() {
        return analyzesDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, day, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.header);
            day = itemView.findViewById(R.id.day);
            price = itemView.findViewById(R.id.price);

        }
    }
}
