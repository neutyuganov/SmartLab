package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.MyViewHolder>   {

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
        holder.price.setText(catalogData.price+ " ₽");

        holder.button_add.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag = false;
                    holder.button_add.setBackgroundResource(R.drawable.button_delite_item_background);
                    holder.button_add.setText("Убрать");

                    holder.button_add.setTextColor(ContextCompat.getColor(context, R.color.buttonLogInActive));
                }
                else {
                    flag = true;
                    holder.button_add.setBackgroundResource(R.drawable.button_add_item_background);
                    holder.button_add.setText("Добавить");
                    holder.button_add.setTextColor(ContextCompat.getColor(context, R.color.white));
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_analyze);

                TextView tv_title = bottomSheetDialog.findViewById(R.id.header_bs);
                TextView tv_description = bottomSheetDialog.findViewById(R.id.description_bs);
                TextView tv_prep = bottomSheetDialog.findViewById(R.id.prep_bs);
                TextView tv_time_result = bottomSheetDialog.findViewById(R.id.time_result_bs);
                TextView tv_bio = bottomSheetDialog.findViewById(R.id.bio_bs);
                Button bt_price = bottomSheetDialog.findViewById(R.id.button_add_bs);

                tv_title.setText(catalogData.title);
                tv_description.setText(catalogData.description);
                tv_prep.setText(catalogData.prep);
                tv_time_result.setText(catalogData.time_result);
                tv_bio.setText(catalogData.bio);
                bt_price.setText("Добавть за " + catalogData.price+ " ₽");

                ImageButton button_back = bottomSheetDialog.findViewById(R.id.button_back_bs);

                    button_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });

                bottomSheetDialog.show();
                }
        });
    }

    @Override
    public int getItemCount() {
        return catalogDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, day, price;

        Button button_add;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.header);
            day = itemView.findViewById(R.id.day);
            price = itemView.findViewById(R.id.price);

            button_add = itemView.findViewById(R.id.button_add_item);
        }
    }

    public void filterList(ArrayList<CatalogData> catalogFilter){
        this.catalogDataList = catalogFilter;
        notifyDataSetChanged();
    }
}
