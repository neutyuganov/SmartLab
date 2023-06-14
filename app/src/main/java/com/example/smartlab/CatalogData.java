package com.example.smartlab;

public class CatalogData {

    String title;
    String price;
    String description;
    String prep;
    String time_result;
    String bio;

    public CatalogData(String title, String price, String description, String prep, String time_result, String bio) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.prep = prep;
        this.time_result = time_result;
        this.bio = bio;
    }

    public String getTitle() {
        return title;
    }

    public String getTime_result() {
        return time_result;
    }

    public String getPrice() {
        return price;
    }
}
