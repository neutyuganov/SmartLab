package com.example.smartlab;

public class CatalogData {

    String title;
    String price;
    String description;
    String preparation;
    String time_result;
    String bio;

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getTime_result() {
        return time_result;
    }

    public String getBio() {
        return bio;
    }

    public String getTitle() {
        return title;
    }

    public CatalogData(String id, String title, String price, String description, String preparation, String time_result, String bio) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.preparation = preparation;
        this.time_result = time_result;
        this.bio = bio;
    }
}
