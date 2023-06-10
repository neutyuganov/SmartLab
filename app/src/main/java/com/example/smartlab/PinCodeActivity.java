package com.example.smartlab;

import static com.example.smartlab.CreateCardActivity.APP_PREFERENCES_CARD_FINISH;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

public class PinCodeActivity extends AppCompatActivity implements View.OnClickListener {

    Button pin1, pin2, pin3, pin4,pin5, pin6, pin7, pin8, pin9, pin0;

    ImageButton backspace;

    View pin_indicator1, pin_indicator2, pin_indicator3, pin_indicator4;

    TextView btn_skip, text, head;

    boolean flag;

    Vibrator vibrator;

    ArrayList<String> numberList = new ArrayList<>();

    String passCode = "";
    String pinNum1, pinNum2, pinNum3, pinNum4;

    private static final String MY_SETTINGS = "my_settings";
    public static final String APP_PREFERENCES_PIN = "passcode";
    public static final String APP_PREFERENCES_SKIP_PIN = "skip_passcode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        setContentView(R.layout.activity_pin_code);
        pin1 = findViewById(R.id.button1);
        pin2 = findViewById(R.id.button2);
        pin3 = findViewById(R.id.button3);
        pin4 = findViewById(R.id.button4);
        pin5 = findViewById(R.id.button5);
        pin6 = findViewById(R.id.button6);
        pin7 = findViewById(R.id.button7);
        pin8 = findViewById(R.id.button8);
        pin9 = findViewById(R.id.button9);
        pin0 = findViewById(R.id.button0);

        pin_indicator1 = findViewById(R.id.indicator1);
        pin_indicator2 = findViewById(R.id.indicator2);
        pin_indicator3 = findViewById(R.id.indicator3);
        pin_indicator4 = findViewById(R.id.indicator4);

        head = findViewById(R.id.head);

        backspace = findViewById(R.id.backspace);

        btn_skip = findViewById(R.id.buttonSkip);

        vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        pin1.setOnClickListener(this);
        pin2.setOnClickListener(this);
        pin3.setOnClickListener(this);
        pin4.setOnClickListener(this);
        pin5.setOnClickListener(this);
        pin6.setOnClickListener(this);
        pin7.setOnClickListener(this);
        pin8.setOnClickListener(this);
        pin9.setOnClickListener(this);
        pin0.setOnClickListener(this);

        backspace.setOnClickListener(this);

        btn_skip.setOnClickListener(this);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);

        if (sp.contains(APP_PREFERENCES_PIN)) {
            head.setText("Введите пароль");
            layout.removeView(btn_skip);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.buttonSkip){
            SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            if(flag){
                sp.edit().remove(APP_PREFERENCES_PIN).apply();
                passCode(numberList);
                head.setText("Создайте пароль");
                btn_skip.setText("Пропустить");
                numberList.clear();
                passCode(numberList);
                flag = false;
            }
            else {
                editor.putBoolean(APP_PREFERENCES_SKIP_PIN, true);
                editor.apply();
                Intent mainIntent = new Intent(PinCodeActivity.this, CreateCardActivity.class);
                this.startActivity(mainIntent);
                this.finish();
            }
            btn_skip.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        }
        if (id == R.id.button1) {
            numberList.add("1");
            passCode(numberList);
            pin1.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button2) {
            numberList.add("2");
            passCode(numberList);
            pin2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button3) {
            numberList.add("3");
            passCode(numberList);
            pin3.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button4) {
            numberList.add("4");
            passCode(numberList);
            pin4.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button5) {
            numberList.add("5");
            passCode(numberList);
            pin5.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button6) {
            numberList.add("6");
            passCode(numberList);
            pin6.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button7) {
            numberList.add("7");
            passCode(numberList);
            pin7.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button8) {
            numberList.add("8");
            passCode(numberList);
            pin8.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button9) {
            numberList.add("9");
            passCode(numberList);
            pin9.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.button0) {
            numberList.add("0");
            passCode(numberList);
            backspace.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        } else if (id == R.id.backspace) {
            switch (numberList.size()){
                case 1:
                    numberList.remove(0);
                    break;
                case 2:
                    numberList.remove(1);
                    break;
                case 3:
                    numberList.remove(2);
                    break;
                case 4:
                    numberList.remove(3);
                    break;
            }
            passCode(numberList);
            backspace.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        }
    }

    private void passCode(ArrayList<String> numberList) {
        if(numberList.size() == 0){
            pin_indicator1.setBackgroundResource(R.drawable.disenabled_indicator);
            pin_indicator2.setBackgroundResource(R.drawable.disenabled_indicator);
            pin_indicator3.setBackgroundResource(R.drawable.disenabled_indicator);
            pin_indicator4.setBackgroundResource(R.drawable.disenabled_indicator);
        }
        else {
            switch (numberList.size()){
                case 1:
                    pinNum1 = numberList.get(0);
                    pin_indicator1.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator2.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin_indicator3.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin_indicator4.setBackgroundResource(R.drawable.disenabled_indicator);
                    break;
                case 2:
                    pinNum2 = numberList.get(1);
                    pin_indicator1.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator2.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator3.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin_indicator4.setBackgroundResource(R.drawable.disenabled_indicator);
                    break;
                case 3:
                    pinNum3 = numberList.get(2);
                    pin_indicator1.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator2.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator3.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator4.setBackgroundResource(R.drawable.disenabled_indicator);
                    break;
                case 4:
                    pinNum4 = numberList.get(3);
                    pin_indicator1.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator2.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator3.setBackgroundResource(R.drawable.enabled_indicator);
                    pin_indicator4.setBackgroundResource(R.drawable.enabled_indicator);
                    passCodeFinish();
                    passCode = pinNum1+pinNum2+pinNum3+pinNum4;

                    if(getPassCode().length()==0){
                        savePassCode(passCode);
                        flag = true;
                    }
                    else{
                        matchPassCode();
                    }
                    break;
            }
        }
    }

    private void matchPassCode() {
        if(getPassCode().equals(passCode)){
            pin_indicator1.setBackgroundResource(R.drawable.access_indicator);
            pin_indicator2.setBackgroundResource(R.drawable.access_indicator);
            pin_indicator3.setBackgroundResource(R.drawable.access_indicator);
            pin_indicator4.setBackgroundResource(R.drawable.access_indicator);

            head.setText("Вы вошли");

            SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
            if(!sp.contains(APP_PREFERENCES_CARD_FINISH)){
                startActivity(new Intent(this, CreateCardActivity.class));
                this.finish();
            }
            else{
                startActivity(new Intent(this, MainActivity.class));
                this.finish();
            }

        }
        else {

            new CountDownTimer(500,500){

                @Override
                public void onTick(long l) {
                    vibrator.vibrate(500);
                    pin_indicator1.setBackgroundResource(R.drawable.error_indicator);
                    pin_indicator2.setBackgroundResource(R.drawable.error_indicator);
                    pin_indicator3.setBackgroundResource(R.drawable.error_indicator);
                    pin_indicator4.setBackgroundResource(R.drawable.error_indicator);
                    pin0.setEnabled(false);
                    pin1.setEnabled(false);
                    pin2.setEnabled(false);
                    pin3.setEnabled(false);
                    pin4.setEnabled(false);
                    pin5.setEnabled(false);
                    pin6.setEnabled(false);
                    pin7.setEnabled(false);
                    pin8.setEnabled(false);
                    pin9.setEnabled(false);
                    pin0.setEnabled(false);
                }

                @Override
                public void onFinish() {
                    numberList.clear();
                    pin_indicator1.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin_indicator2.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin_indicator3.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin_indicator4.setBackgroundResource(R.drawable.disenabled_indicator);
                    pin0.setEnabled(true);
                    pin1.setEnabled(true);
                    pin2.setEnabled(true);
                    pin3.setEnabled(true);
                    pin4.setEnabled(true);
                    pin5.setEnabled(true);
                    pin6.setEnabled(true);
                    pin7.setEnabled(true);
                    pin8.setEnabled(true);
                    pin9.setEnabled(true);
                    pin0.setEnabled(true);
                }
            }.start();
        }
    }

    private SharedPreferences.Editor savePassCode(String passCode) {
        SharedPreferences preferences = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(APP_PREFERENCES_PIN, passCode);
        editor.apply();
        return editor;
    }

    private String getPassCode() {
        SharedPreferences preferences = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        return preferences.getString(APP_PREFERENCES_PIN, "");
    }

    private void passCodeFinish(){
        new CountDownTimer(250,50){
            @Override
            public void onTick(long l) {
                pin0.setEnabled(false);
                pin1.setEnabled(false);
                pin2.setEnabled(false);
                pin3.setEnabled(false);
                pin4.setEnabled(false);
                pin5.setEnabled(false);
                pin6.setEnabled(false);
                pin7.setEnabled(false);
                pin8.setEnabled(false);
                pin9.setEnabled(false);
                pin0.setEnabled(false);
            }

            @Override
            public void onFinish() {
                numberList.clear();
                pin_indicator1.setBackgroundResource(R.drawable.disenabled_indicator);
                pin_indicator2.setBackgroundResource(R.drawable.disenabled_indicator);
                pin_indicator3.setBackgroundResource(R.drawable.disenabled_indicator);
                pin_indicator4.setBackgroundResource(R.drawable.disenabled_indicator);

                if(flag){
                    head.setText("Повторите пароль");
                    btn_skip.setText("Отмена");
                }

                pin0.setEnabled(true);
                pin1.setEnabled(true);
                pin2.setEnabled(true);
                pin3.setEnabled(true);
                pin4.setEnabled(true);
                pin5.setEnabled(true);
                pin6.setEnabled(true);
                pin7.setEnabled(true);
                pin8.setEnabled(true);
                pin9.setEnabled(true);
                pin0.setEnabled(true);
            }
        }.start();
    }
}