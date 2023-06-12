package com.example.smartlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView menuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        menuView = findViewById(R.id.bottomNavigationView);
        menuView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAnalyzes()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.navigation_analyzes) {
                if (selectedFragment == new FragmentAnalyzes()) {
                    return false;
                }
                else selectedFragment = new FragmentAnalyzes();
            }
            if (id == R.id.navigation_results) {
                selectedFragment = new FragmentResults();
            }
            if (id == R.id.navigation_profile) {
                selectedFragment = new FragmentProfile();
            }
            if (id == R.id.navigation_help) {
                selectedFragment = new FragmentHelp();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}