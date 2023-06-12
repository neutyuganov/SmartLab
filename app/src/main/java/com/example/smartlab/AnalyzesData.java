package com.example.smartlab;

public class AnalyzesData {

    String title;
    String day;
    String price;

    public AnalyzesData(String title, String day, String price) {
        this.title = title;
        this.day = day;
        this.price = price;
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
