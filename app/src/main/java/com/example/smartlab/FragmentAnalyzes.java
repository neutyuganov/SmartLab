package com.example.smartlab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentAnalyzes extends Fragment {

    ArrayList<AnalyzesData> analyzesDataList;

    RecyclerView recyclerView;
    private String[] analyzesDataTitle;
    private String[] analyzesDataDay;
    private String[] analyzesDataPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analyzes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        /*String[] analyzesDataTitle = getResources().getStringArray(R.array.analyzes_title);
        String[] analyzesDataDay = getResources().getStringArray(R.array.analyzes_day);
        String[] analyzesDataPrice = getResources().getStringArray(R.array.analyzes_price);*/

        analyzesDataList = new ArrayList<>();

        analyzesDataTitle = new String[]{
                getString(R.string.header1),
                getString(R.string.header2),
                getString(R.string.header3),
                getString(R.string.header4),
                getString(R.string.header5),
                getString(R.string.header6)
        };

        analyzesDataDay = new String[]{
                getString(R.string.day1),
                getString(R.string.day2),
                getString(R.string.day3),
                getString(R.string.day4),
                getString(R.string.day5),
                getString(R.string.day6)
        };

        analyzesDataPrice = new String[]{
                getString(R.string.price1),
                getString(R.string.price2),
                getString(R.string.price3),
                getString(R.string.price4),
                getString(R.string.price5),
                getString(R.string.price6)
        };

        for(int i = 0; i<analyzesDataTitle.length; i++){
            AnalyzesData analyzesData = new AnalyzesData(analyzesDataTitle[i], analyzesDataDay[i], analyzesDataPrice[i]);
            analyzesDataList.add(analyzesData);
        }

        recyclerView = view.findViewById(R.id.analyzesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AnalyzesAdapter analyzesAdapter = new AnalyzesAdapter(getContext(), analyzesDataList);
        recyclerView.setAdapter(analyzesAdapter);
        analyzesAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {


    }
}