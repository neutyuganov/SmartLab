package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OnboardActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        setOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);
    }

    private void setOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemAnalyzes = new OnboardingItem();
        itemAnalyzes.setTitle("String.valueOf(R.string.title_analyzes)");
        itemAnalyzes.setDescription("String.valueOf(R.string.text_analyzes)");
        itemAnalyzes.setImage(R.drawable.image_analyzes);

        OnboardingItem itemNotifications = new OnboardingItem();
        itemNotifications.setTitle("String.valueOf(R.string.title_notifications)");
        itemNotifications.setDescription("String.valueOf(R.string.text_notifications)");
        itemNotifications.setImage(R.drawable.images_notifications);

        OnboardingItem itemMonitoring = new OnboardingItem();
        itemMonitoring.setTitle("String.valueOf(R.string.title_monitoring)");
        itemMonitoring.setDescription("String.valueOf(R.string.text_monitoring)");
        itemMonitoring.setImage(R.drawable.image_monitoring);

        onboardingItems.add(itemAnalyzes);
        onboardingItems.add(itemNotifications);
        onboardingItems.add(itemMonitoring);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);


    }
}