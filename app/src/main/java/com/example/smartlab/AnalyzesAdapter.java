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

public class AnalyzesAdapter extends RecyclerView.Adapter<AnalyzesAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<AnalyzesData> analyzesDataList;

    ArrayList<AnalyzesData> analyzesDataListFull;

    public AnalyzesAdapter(Context context, ArrayList<AnalyzesData> analyzesDataList){
        this.context = context;
        this.analyzesDataList = analyzesDataList;
        analyzesDataListFull = new ArrayList<>(analyzesDataList);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_analyze);
                bottomSheetDialog.show();
            }
        });
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

    @Override
    public Filter getFilter() {
        return analyzeFilter;
    }

    private Filter analyzeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AnalyzesData> filteredList = new ArrayList<>();

            if(constraint == null | constraint.length() == 0){
                filteredList.addAll(analyzesDataListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(AnalyzesData item: analyzesDataListFull){
                    if(item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            analyzesDataList.clear();
            analyzesDataList.addAll((List)results.values);
        }
    };
}
