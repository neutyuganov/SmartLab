package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class CreateCardActivity extends AppCompatActivity {

    private static final String MY_SETTINGS = "my_settings";
    public static final String APP_PREFERENCES_CARD_FINISH = "cardFinish";


    EditText name, secondName, patronymic, dateBirthDay;

    Spinner gender;

    Button button_create_card;

    int year_x, month_x, day_x;
    static final int DIALOG_DATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        setContentView(R.layout.activity_create_card);

        name = findViewById(R.id.editTextName);
        secondName = findViewById(R.id.editTextSecondName);
        patronymic = findViewById(R.id.editTextPatronymic);
        dateBirthDay = findViewById(R.id.editTextBirthDay);

        button_create_card = findViewById(R.id.buttonCreate);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        gender = findViewById(R.id.gender);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!name.getText().toString().trim().isEmpty()) {
                    name.setBackgroundResource(R.drawable.edittext_focus);
                } else {
                    name.setBackgroundResource(R.drawable.edittext_unfocus);
                }
                checkNoEmptyItems();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        secondName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!secondName.getText().toString().trim().isEmpty()) {
                    secondName.setBackgroundResource(R.drawable.edittext_focus);
                } else {
                    secondName.setBackgroundResource(R.drawable.edittext_unfocus);
                }
                checkNoEmptyItems();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        patronymic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!patronymic.getText().toString().trim().isEmpty()) {
                    patronymic.setBackgroundResource(R.drawable.edittext_focus);
                } else {
                    patronymic.setBackgroundResource(R.drawable.edittext_unfocus);
                }
                checkNoEmptyItems();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        dateBirthDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!dateBirthDay.getText().toString().trim().isEmpty()) {
                    dateBirthDay.setBackgroundResource(R.drawable.edittext_focus);
                } else {
                    dateBirthDay.setBackgroundResource(R.drawable.edittext_unfocus);
                }
                checkNoEmptyItems();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        showDialogOnButtonClick();

        button_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(APP_PREFERENCES_CARD_FINISH, true);
                editor.apply();
                Intent i = new Intent(CreateCardActivity.this, MenuActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
    }

   public void showDialogOnButtonClick(){
        dateBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });
   }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, dateSetListener, year_x, month_x, day_x);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            year_x = year;
            day_x = day;
            month_x = month + 1;

            dateBirthDay.setText(day_x + "." + month_x + "." + year_x);
        }
    };

    private void checkNoEmptyItems(){
        if(name.getText().toString().length() != 0 & secondName.getText().toString().length() != 0 & patronymic.getText().toString().length() != 0 & dateBirthDay.getText().toString().length() != 0){
            button_create_card.setEnabled(true);
        }
        else {
            button_create_card.setEnabled(false);
        }
    }
}
