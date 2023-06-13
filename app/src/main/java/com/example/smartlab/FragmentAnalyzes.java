package com.example.smartlab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

public class FragmentAnalyzes extends Fragment {


    AnalyzesAdapter analyzesAdapter;
    ArrayList<AnalyzesData> analyzesDataList;

    SearchView searchView;

    RecyclerView recyclerView;
    private String[] analyzesDataTitle;
    private String[] analyzesDataDay;
    private String[] analyzesDataPrice;
    private String[] analyzesDataDescription;
    private String[] analyzesDataPrep;
    private String[] analyzesDataBio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analyzes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        for(int i = 0; i<analyzesDataTitle.length; i++){
            AnalyzesData analyzesData = new AnalyzesData(analyzesDataTitle[i],
                    analyzesDataPrice[i],
                    analyzesDataDescription[i],
                    analyzesDataPrep[i],
                    analyzesDataDay[i],
                    analyzesDataBio[i]);
            analyzesDataList.add(analyzesData);
        }

        /*searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("newText", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("newText", newText);
                analyzesAdapter.getFilter().filter(newText);
                return true;
            }
        });*/

        recyclerView = view.findViewById(R.id.analyzesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AnalyzesAdapter analyzesAdapter = new AnalyzesAdapter(getContext(), analyzesDataList);
        recyclerView.setAdapter(analyzesAdapter);
    }

    private void dataInitialize() {
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

        analyzesDataPrep = new String[]{
                getString(R.string.price1),
                getString(R.string.price2),
                getString(R.string.price3),
                getString(R.string.price4),
                getString(R.string.price5),
                getString(R.string.price6)
        };

        analyzesDataDescription = new String[]{
                getString(R.string.price1),
                getString(R.string.price2),
                getString(R.string.price3),
                getString(R.string.price4),
                getString(R.string.price5),
                getString(R.string.price6)
        };

        analyzesDataBio = new String[]{
                getString(R.string.price1),
                getString(R.string.price2),
                getString(R.string.price3),
                getString(R.string.price4),
                getString(R.string.price5),
                getString(R.string.price6)
        };
    }
}