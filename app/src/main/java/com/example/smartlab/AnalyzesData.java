package com.example.smartlab;

public class AnalyzesData {

    String title;
    String price;
    String description;
    String prep;
    String day;
    String bio;

    public AnalyzesData(String title, String price, String description, String prep, String day, String bio) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.prep = prep;
        this.day = day;
        this.bio = bio;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getPrice() {
        return price;
    }
}
