package com.example.smartlab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class FragmentAnalyzes extends Fragment {

    CatalogAdapter catalogAdapter;
    ArrayList<CatalogData> catalogDataList;

    SearchView searchView;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analyzes, container, false);
    }
    private void filter (String search){
        ArrayList<CatalogData> filterList = new ArrayList<>();
        for (CatalogData item : catalogDataList){
            if (item.getTitle().toLowerCase().contains(search.toLowerCase())){
                filterList.add(item);
            }
        }
        catalogAdapter.filterList(filterList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        searchView = view.findViewById(R.id.searchView);

        RelativeLayout layout = view.findViewById(R.id.layoutFull);

        ConstraintLayout layoutCart = view.findViewById(R.id.layout_cart);

//        layout.removeView(layoutCart);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("newText", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("newText", newText);
                filter(newText);
                return false;
            }
        });

        recyclerView = view.findViewById(R.id.analyzesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        catalogAdapter = new CatalogAdapter(getContext(), catalogDataList);
        recyclerView.setAdapter(catalogAdapter);
    }

    private void dataInitialize() {
        catalogDataList = new ArrayList<>();

        catalogDataList.add(new CatalogData("ПЦР-тест на определение РНК коронавируса стандартный", 1800, "Описание ПЦР-тест на определение РНКкоронавируса стандартный", "Подготовка к ПЦР-тест на определение РНК коронавируса стандартный", "2 дня", "Венозная кровь"));
        catalogDataList.add(new CatalogData("ПЦР-тест на определение РНК коронавируса стандартный", 1200, "Описание ПЦР-тест на определение РНКкоронавируса стандартный", "Подготовка к ПЦР-тест на определение РНК коронавируса стандартный", "2 дня", "Венозная кровь"));
        catalogDataList.add(new CatalogData("Биохимический анализ крови, базовый", 690, "Описание Биохимический анализ крови, базовый", "Подготовка к Биохимический анализ крови, базовый", "2 дня", "биология"));
        catalogDataList.add(new CatalogData("Биохимический анализ крови, не базовый", 900, "Описание Биохимический анализ крови, не базовый", "Подготовка к Биохимический анализ крови, не базовый", "1 день", "Крутая кровь"));
        catalogDataList.add(new CatalogData("СОЭ (капиллярная кровь)", 120, "Описание СОЭ (капиллярная кровь)", "Подготовка к СОЭ (капиллярная кровь)", "2 дня", "кровь"));
        catalogDataList.add(new CatalogData("Клинический анализ крови с лейкоцитарной формулировкой", 300, "Описание Клинический анализ крови с лейкоцитарной формулировкой", "Подготовка к Клинический анализ крови с лейкоцитарной формулировкой", "1 день", "Аретериальная кровь"));
    }
}