package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>   {

    private final Context context;
    private final List<Object> listRecyclerItem;
    public CategoryAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;}
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Присваиваем поля для заполнения элемента RecyclerView
        TextView id, category, name, description, price, time_result, preparation, bio;
        public ItemViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.button_category);
        }}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ItemViewHolder((v));}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        ItemViewHolder _holder = (ItemViewHolder) holder;
        CategoryData catalog = (CategoryData) listRecyclerItem.get(position);
        _holder.name.setText(catalog.getTitle());

// Пример реализации setOnClickListener для этого представления
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, catalog.getTitle(), Toast.LENGTH_SHORT).show();}});}
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return listRecyclerItem.size();}}

