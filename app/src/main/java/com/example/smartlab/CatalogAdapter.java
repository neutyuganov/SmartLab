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

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>   {

    private final Context context;
    private final List<Object> listRecyclerItem;

    public CatalogAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;}

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Присваиваем поля для заполнения элемента RecyclerView
        TextView id, category, name, description, price, time_result, preparation, bio;
        Button button_add;

        public ItemViewHolder(View itemView) {
            super(itemView);
            button_add = itemView.findViewById(R.id.button_add_item);
            name=(TextView) itemView.findViewById(R.id.header_catalog);
            time_result=(TextView) itemView.findViewById(R.id.time_result_catalog);
            price=(TextView) itemView.findViewById(R.id.price_catalog);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_analyze, parent, false);
        return new ItemViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        ItemViewHolder _holder = (ItemViewHolder) holder;
        CatalogData catalog = (CatalogData) listRecyclerItem.get(position);
        _holder.name.setText(catalog.getTitle());
        _holder.time_result.setText(catalog.getTime_result());
        _holder.price.setText(catalog.getPrice());

        _holder.button_add.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag = false;
                    _holder.button_add.setBackgroundResource(R.drawable.button_delite_item_background);
                    _holder.button_add.setText("Убрать");

                    _holder.button_add.setTextColor(ContextCompat.getColor(context, R.color.buttonLogInActive));
                }
                else {
                    flag = true;
                    _holder.button_add.setBackgroundResource(R.drawable.button_add_item_background);
                    _holder.button_add.setText("Добавить");
                    _holder.button_add.setTextColor(ContextCompat.getColor(context, R.color.white));
                }
            }
        });

// Пример реализации setOnClickListener для этого представления
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_analyze);

                TextView tv_title = bottomSheetDialog.findViewById(R.id.header_bs);
                TextView tv_description = bottomSheetDialog.findViewById(R.id.description_bs);
                TextView tv_prep = bottomSheetDialog.findViewById(R.id.prep_bs);
                TextView tv_time_result = bottomSheetDialog.findViewById(R.id.time_result_bs);
                TextView tv_bio = bottomSheetDialog.findViewById(R.id.bio_bs);
                Button bt_price = bottomSheetDialog.findViewById(R.id.button_add_bs);

                tv_title.setText(catalog.title);
                tv_description.setText(catalog.description);
                tv_prep.setText(catalog.preparation);
                tv_time_result.setText(catalog.time_result);
                tv_bio.setText(catalog.bio);
                bt_price.setText("Добавть за " + catalog.price+ " ₽");

                ImageButton button_back = bottomSheetDialog.findViewById(R.id.button_back_bs);

                    button_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });

                bottomSheetDialog.show();
            }});
    }
    @Override
    public int getItemCount() {
// Получает всё количесво элементов RecyclerView
        return listRecyclerItem.size();
    }

//    public void filterList(ArrayList<CatalogData> catalogFilter){
//        this.catalogDataList = catalogFilter;
//        notifyDataSetChanged();
//    }
}

