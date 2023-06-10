package com.example.smartlab;

import static com.example.smartlab.CreateCardActivity.APP_PREFERENCES_CARD_FINISH;
import static com.example.smartlab.PinCodeActivity.APP_PREFERENCES_PIN;
import static com.example.smartlab.PinCodeActivity.APP_PREFERENCES_SKIP_PIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.provider.ContactsContract;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;
    private static final String MY_SETTINGS = "my_settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                        Context.MODE_PRIVATE);

                boolean hasVisited = sp.getBoolean("hasVisited", false);
                boolean hasPinSkip = sp.getBoolean(APP_PREFERENCES_SKIP_PIN, false);
                boolean hasFinishCard = sp.getBoolean(APP_PREFERENCES_CARD_FINISH, false);

                if (!hasVisited) {
                    SharedPreferences.Editor e = sp.edit();
                    e.putBoolean("hasVisited", true);
                    e.apply();
                    Intent i = new Intent(SplashScreen.this, OnboardActivity.class);
                    SplashScreen.this.startActivity(i);
                    SplashScreen.this.finish();
                }
                else {
                    /*if(hasFinishCard){
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        SplashScreen.this.startActivity(i);
                        SplashScreen.this.finish();
                    }*/
                    if(sp.contains(APP_PREFERENCES_PIN)) {
                        Intent i = new Intent(SplashScreen.this, PinCodeActivity.class);
                        SplashScreen.this.startActivity(i);
                        SplashScreen.this.finish();
                    }
                    else if (!sp.contains(APP_PREFERENCES_PIN) & hasPinSkip){
                        Intent i = new Intent(SplashScreen.this, CreateCardActivity.class);
                        SplashScreen.this.startActivity(i);
                        SplashScreen.this.finish();
                    }
                    else {
                        Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                        SplashScreen.this.startActivity(i);
                        SplashScreen.this.finish();
                    }
                }
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}