package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class OnboardActivity extends AppCompatActivity {

    ViewPager2 viewPager;

    ArrayList<OnboardingItem> viewPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardViewPager);
        int[] images = {R.drawable.image_analyzes, R.drawable.images_notifications, R.drawable.image_monitoring};
        String[] title = {getString(R.string.title_analyzes), getString(R.string.title_notifications), getString(R.string.title_monitoring)};
        String[] text = {getString(R.string.text_analyzes), getString(R.string.text_notifications), getString(R.string.text_monitoring)};

        viewPagerItemArrayList = new ArrayList<>();

        for(int i = 0; i < images.length; i++){
            OnboardingItem onboardingItem = new OnboardingItem(images[i], title[i], text[i]);
            viewPagerItemArrayList.add(onboardingItem);

        }
        OnboardingAdapter adapter =new OnboardingAdapter(viewPagerItemArrayList);

        onboardingViewPager.setClipToPadding(false);
        onboardingViewPager.setClipChildren(false);
        onboardingViewPager.setOffscreenPageLimit(2);
        onboardingViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        onboardingViewPager.setAdapter(adapter);


    }
}