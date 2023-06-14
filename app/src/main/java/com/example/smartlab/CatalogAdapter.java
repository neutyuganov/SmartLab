package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.MyViewHolder> /*implements Filterable*/ {

    Context context;
    ArrayList<CatalogData> catalogDataList;

    public CatalogAdapter(Context context, ArrayList<CatalogData> catalogDataList){
        this.context = context;
        this.catalogDataList = catalogDataList;
    }

    @NonNull
    @Override
    public CatalogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_analyze, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.MyViewHolder holder, int position) {
        CatalogData catalogData = catalogDataList.get(position);
        holder.title.setText(catalogData.title);
        holder.day.setText(catalogData.time_result);
        holder.price.setText(catalogData.price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_analyze);
                bottomSheetDialog.show();

                /*holder.title_bs.setText(analyzesData.title);
                holder.day_bs.setText(analyzesData.day);
                holder.description_bs.setText(analyzesData.description);
                holder.bio_bs.setText(analyzesData.bio);
                holder.prep_bs.setText(analyzesData.prep);    */        }
        });
    }

    @Override
    public int getItemCount() {
        return catalogDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, day, price;

//        TextView title_bs, day_bs, price_bs, description_bs, prep_bs, bio_bs;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            /*title_bs = itemView.findViewById(R.id.header_bs);
            day_bs = itemView.findViewById(R.id.day_bs);
            description_bs = itemView.findViewById(R.id.description_bs);
            prep_bs = itemView.findViewById(R.id.prep_bs);
            bio_bs = itemView.findViewById(R.id.bio_bs);*/

            title = itemView.findViewById(R.id.header);
            day = itemView.findViewById(R.id.day);
            price = itemView.findViewById(R.id.price);
        }
    }

    public void filterList(ArrayList<CatalogData> catalogFilter){
        this.catalogDataList = catalogFilter;
        notifyDataSetChanged();
    }
}
