package com.example.smartlab;

public class NewsData {

    String id;
    String price;



    String title;
    String description;

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public NewsData(String id, String title, String description, String price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }


}
