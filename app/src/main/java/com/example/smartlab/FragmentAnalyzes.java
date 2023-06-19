package com.example.smartlab;

import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentAnalyzes extends Fragment {
    Button button_cart;
    TextView textView_price;

    SearchView searchView;

    JSONArray arrayNews;
    JSONArray arrayCategory;


    private RecyclerView recyclerViewNews;
    private RecyclerView recyclerViewCategory;


    private List<Object> viewItemsNews = new ArrayList<>();
    private List<Object> viewItemsCategory = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_analyzes, container, false);

        new GetTaskNews().execute(new JSONObject());
        new GetTaskCataegory().execute(new JSONObject());


        recyclerViewNews=(RecyclerView) v.findViewById(R.id.newsRecyclerView);
        recyclerViewCategory=(RecyclerView) v.findViewById(R.id.categoryRecyclerView);


// Присваиваем LayoutManager что бы изменить направление RecyclerView
        NewsAdapter adapterNews = new NewsAdapter(getContext(), viewItemsNews);
        CategoryAdapter adapterCategory = new CategoryAdapter(getContext(), viewItemsCategory);


        recyclerViewNews.setAdapter(adapterNews);
        recyclerViewCategory.setAdapter(adapterCategory);


        return v;
    }

//    private void filter (String search){
//        ArrayList<CatalogData> filterList = new ArrayList<>();
//        for (CatalogData item : catalogDataList){
//            if (item.getTitle().toLowerCase().contains(search.toLowerCase())){
//                filterList.add(item);
//            }
//        }
//        catalogAdapter.filterList(filterList);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchView);

        button_cart = view.findViewById(R.id.button_cart);
        textView_price = view.findViewById(R.id.textView_price);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d("newText", query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d("newText", newText);
//                filter(newText);
//                return false;
//            }
//        });
    }

    private class GetTaskNews extends AsyncTask<JSONObject, Void, String> {
        @Override
            protected String doInBackground (JSONObject...jsonObjects){
                try {
                    InputStream stream = null;
// Для буферизации текста из потока
                    BufferedReader reader = null;
                    HttpURLConnection connection = null;
                    try {
// Присваиваем путь
                        URL url = new URL("http://10.0.2.2:8000/api/news/");
                        connection = (HttpURLConnection) url.openConnection();
// Выбираем метод GET для запроса
                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(10000);
                        connection.connect();
// Полученный результат разбиваем с помощью байтовых потоков
                        stream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuilder buf = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            buf.append(line).append("\n");
                        }
                        // Возвращаем разбитый по строкам результат
                        JSONObject root = new JSONObject(buf.toString());
                        arrayNews= root.getJSONArray("results");
                        addItemsFromJSONnews();
                        return(buf.toString());
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

    private class GetTaskCataegory extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground (JSONObject...jsonObjects){
            try {
                InputStream stream = null;
// Для буферизации текста из потока
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
// Присваиваем путь
                    URL url = new URL("http://10.0.2.2:8000/api/category/");
                    connection = (HttpURLConnection) url.openConnection();
// Выбираем метод GET для запроса
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.connect();
// Полученный результат разбиваем с помощью байтовых потоков
                    stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buf = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buf.append(line).append("\n");
                    }
                    // Возвращаем разбитый по строкам результат
                    JSONObject root = new JSONObject(buf.toString());
                    arrayCategory= root.getJSONArray("results");
                    addItemsFromJSONcategory();
                    return(buf.toString());
                } catch (Exception e) {
                    e.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }



    private void addItemsFromJSONnews() {
            try {
// Заполняем Модель спаршенными данными
                for (int i = 0; i < arrayNews.length(); ++i) {
                    JSONObject itemObj = arrayNews.getJSONObject(i);
                    String id = itemObj.getString("id");
                    String name = itemObj.getString("name");
                    String description = itemObj.getString("description");
                    String price = itemObj.getString("price");
                    NewsData catalogs = new NewsData(id, name, description, price);
                    viewItemsNews.add(catalogs);
                }
            } catch (JSONException e) {
            }
        }

    private void addItemsFromJSONcategory() {
        try {
// Заполняем Модель спаршенными данными
            for (int i = 0; i < arrayCategory.length(); ++i) {
                JSONObject itemObj = arrayCategory.getJSONObject(i);
                String id = itemObj.getString("id");
                String name = itemObj.getString("name");
                CategoryData catalogs = new CategoryData(id, name);
                viewItemsCategory.add(catalogs);
            }
        } catch (JSONException e) {
        }
    }


    }
