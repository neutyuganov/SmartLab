package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.login_activity);

        EditText email = (EditText)findViewById(R.id.edit_text_email);
        Button bt_login = (Button) findViewById(R.id.button_go_login);
        Button bt_login_yandex = (Button) findViewById(R.id.button_go_login_yandex);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    bt_login.setEnabled(true);
                }
                else bt_login.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        }
    }
