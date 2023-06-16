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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>   {

    Context context;
    ArrayList<CategoryData> categoryDataList;

    public CategoryAdapter(Context context, ArrayList<CategoryData> categoryDataList){
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        CategoryData categoryData = categoryDataList.get(position);
        holder.title_category.setText(categoryData.title);
//        holder.title_category.setOnClickListener(new View.OnClickListener() {
//            boolean flag = true;
//            @Override
//            public void onClick(View v) {
//                if(flag) {
//                    flag = false;
//                    holder.button_add.setBackgroundResource(R.drawable.button_delite_item_background);
//                    holder.button_add.setText("Удалить");
//                    holder.button_add.setTextColor(ContextCompat.getColor(context, R.color.buttonLogInActive));
//                }
//                else {
//                    flag = true;
//                    holder.button_add.setBackgroundResource(R.drawable.button_add_item_background);
//                    holder.button_add.setText("Добавить");
//                    holder.button_add.setTextColor(ContextCompat.getColor(context, R.color.white));
//                }
//            }
//        });
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
//                bottomSheetDialog.setContentView(R.layout.bottom_sheet_analyze);
//
//                Button title_category = bottomSheetDialog.findViewById(R.id.button_category);
//
//                title_category.setText(categoryData.title);
//
//                ImageButton button_back = bottomSheetDialog.findViewById(R.id.button_back_bs);
//
//                    button_back.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            bottomSheetDialog.dismiss();
//                        }
//                    });
//
//                bottomSheetDialog.show();
//                }
//        });
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button title_category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_category = itemView.findViewById(R.id.button_category);
        }
    }
}
