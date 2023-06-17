package com.example.smartlab;

public class NewsData {

    int price;
    String title;
    String description;

    public NewsData(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }
}
