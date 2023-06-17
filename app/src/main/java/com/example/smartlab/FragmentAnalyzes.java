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
    CategoryAdapter categoryAdapter;
    NewsAdapter newsAdapter;


    ArrayList<CatalogData> catalogDataList;
    ArrayList<CategoryData> categoryDataList;
    ArrayList<NewsData> newsDataList;


    SearchView searchView;


    RecyclerView recyclerViewCatalog;
    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewNews;


    RelativeLayout layout;
    ConstraintLayout layoutCart;

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

        layout = view.findViewById(R.id.layoutFull);

        layoutCart = view.findViewById(R.id.layout_cart);
        

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

        recyclerViewCatalog = view.findViewById(R.id.catalogRecyclerView);
        recyclerViewCatalog.setLayoutManager(new LinearLayoutManager(getContext() ));
        recyclerViewCatalog.setHasFixedSize(true);
        catalogAdapter = new CatalogAdapter(getContext(), catalogDataList);
        recyclerViewCatalog.setAdapter(catalogAdapter);


        recyclerViewCategory = view.findViewById(R.id.categoryRecyclerView);
        recyclerViewCategory.setHasFixedSize(true);
        categoryAdapter = new CategoryAdapter(getContext(), categoryDataList);
        recyclerViewCategory.setAdapter(categoryAdapter);


        recyclerViewNews = view.findViewById(R.id.newsRecyclerView);
        recyclerViewNews.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getContext(), newsDataList);
        recyclerViewNews.setAdapter(newsAdapter);
    }

    public void getButton(){
        layout.addView(layoutCart);
    }
    private void dataInitialize() {
        catalogDataList = new ArrayList<>();
        catalogDataList.add(new CatalogData("ПЦР-тест на определение РНК коронавируса стандартный", 1800, "Описание ПЦР-тест на определение РНКкоронавируса стандартный", "Подготовка к ПЦР-тест на определение РНК коронавируса стандартный", "2 дня", "Венозная кровь"));
        catalogDataList.add(new CatalogData("ПЦР-тест на определение РНК коронавируса стандартный", 1200, "Описание ПЦР-тест на определение РНКкоронавируса стандартный", "Подготовка к ПЦР-тест на определение РНК коронавируса стандартный", "2 дня", "Венозная кровь"));
        catalogDataList.add(new CatalogData("Биохимический анализ крови, базовый", 690, "Описание Биохимический анализ крови, базовый", "Подготовка к Биохимический анализ крови, базовый", "2 дня", "биология"));
        catalogDataList.add(new CatalogData("Биохимический анализ крови, не базовый", 900, "Описание Биохимический анализ крови, не базовый", "Подготовка к Биохимический анализ крови, не базовый", "1 день", "Крутая кровь"));
        catalogDataList.add(new CatalogData("СОЭ (капиллярная кровь)", 120, "Описание СОЭ (капиллярная кровь)", "Подготовка к СОЭ (капиллярная кровь)", "2 дня", "кровь"));
        catalogDataList.add(new CatalogData("Клинический анализ крови с лейкоцитарной формулировкой", 300, "Описание Клинический анализ крови с лейкоцитарной формулировкой", "Подготовка к Клинический анализ крови с лейкоцитарной формулировкой", "1 день", "Аретериальная кровь"));


        categoryDataList = new ArrayList<>();
        categoryDataList.add(new CategoryData("Популярное"));
        categoryDataList.add(new CategoryData("Covid"));
        categoryDataList.add(new CategoryData("Комплексные"));
        categoryDataList.add(new CategoryData("Чекапы"));

        newsDataList = new ArrayList<>();
        newsDataList.add(new NewsData("Заголовок1", "Описание заголовка1", 8000));
        newsDataList.add(new NewsData("Заголовок2", "Описание заголовка2", 5000));
        newsDataList.add(new NewsData("Заголовок3", "Описание заголовка3", 6700));
        newsDataList.add(new NewsData("Заголовок4", "Описание заголовка4", 300));
    }
}