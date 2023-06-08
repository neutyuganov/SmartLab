package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EmailVerifyActivity extends AppCompatActivity {

    Integer[] correct_code = {0,0,0,0};
    EditText pin1;
    EditText pin2;
    EditText pin3;
    EditText pin4;
    TextView timer;
    TextView correctPin;

    ImageButton back;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.activity_email_verify);

        pin1 = findViewById(R.id.editTextPIN1);
        pin2 = findViewById(R.id.editTextPIN2);
        pin3 = findViewById(R.id.editTextPIN3);
        pin4 = findViewById(R.id.editTextPIN4);

        pin1.requestFocus();

        timer = findViewById(R.id.timer);
        correctPin = findViewById(R.id.correctPin);
        timer.setGravity(1);

        vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        back = findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                onBackPressed();
            }

        });

        Integer password[] = new Integer[4];

        StringBuilder sb = new StringBuilder();

        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long l) {
                timer.setText("Отправить код повторно можно\nбудет через " + l/1000 + " секунд");
            }

            @Override
            public void onFinish() {
                timer.setText("Можно отправить код повторно");
            }
        }.start();

        pin1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(sb.length()==1)
                {
                    sb.deleteCharAt(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(sb.length()==0&pin1.length()==1)
                {
                    sb.append(s);
                    password[0]=Integer.valueOf(sb.toString());
                    if(password[0]!=null & password[1]!=null & password[2]!=null & password[3]!=null){
                        correctEmailCode(password,correct_code);
                    }
                    pin2.requestFocus();
                    pin2.setCursorVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sb.length()==0)
                {
                    password[0]=null;
                    pin1.requestFocus();
                }
            }
        });

        pin2.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(sb.length()==1)
                {
                    sb.deleteCharAt(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(sb.length()==0&pin2.length()==1)
                {
                    sb.append(s);
                    password[1]=Integer.valueOf(sb.toString());
                    if(password[0]!=null & password[1]!=null & password[2]!=null & password[3]!=null){
                        correctEmailCode(password,correct_code);
                    }
                    pin3.requestFocus();
                    pin3.setCursorVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sb.length()==0)
                {
                    password[1]=null;
                    pin1.requestFocus();
                }
            }
        });

        pin3.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(sb.length()==1)
                {
                    sb.deleteCharAt(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(sb.length()==0&pin3.length()==1)
                {
                    sb.append(s);
                    password[2]=Integer.valueOf(sb.toString());
                    if(password[0]!=null & password[1]!=null & password[2]!=null & password[3]!=null){
                        correctEmailCode(password,correct_code);
                    }
                    pin4.requestFocus();
                    pin4.setCursorVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sb.length()==0)
                {
                    password[2]=null;
                    pin2.requestFocus();
                }
            }
        });

        pin4.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(sb.length()==1)
                {
                    sb.deleteCharAt(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(sb.length()==0&pin4.length()==1)
                {
                    sb.append(s);
                    password[3]=Integer.valueOf(sb.toString());
                    if(password[0]!=null & password[1]!=null & password[2]!=null & password[3]!=null){
                        correctEmailCode(password,correct_code);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sb.length()==0)
                {
                    password[3]=null;
                    pin3.requestFocus();
                }
            }
        });
    }

    private void correctEmailCode(Integer[] password , Integer[] correct_code) {

        for(int i = 0; i<correct_code.length; i++)
        {
            if(password[i]!=correct_code[i]){
                vibrator.vibrate(500);
                correctPin.setText("Неверный код, попробуйте " + correct_code[0]+correct_code[1]+correct_code[2]+correct_code[3]);
            }
            else if(password[i]==correct_code[i]){
                if(i==correct_code.length-1){
                    Intent intent = new Intent(EmailVerifyActivity.this, PinCodeActivity.class);
                    startActivity(intent);
                    finishAffinity();
                    correctPin.setText(null);
                }
            }

        }
    }
}