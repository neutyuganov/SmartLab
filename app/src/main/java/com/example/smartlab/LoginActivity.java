package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetTask().execute(new JSONObject());

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.login_activity);

        EditText email = (EditText)findViewById(R.id.edit_text_email);
        Button bt_login = (Button) findViewById(R.id.button_go_login);
        Button bt_login_yandex = (Button) findViewById(R.id.button_go_login_yandex);
        Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_login.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent i = new Intent(LoginActivity.this, EmailVerifyActivity.class);
                startActivity(i);


            }
        });

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

    private class GetTask extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                String parammetrs = "name=1&job=XXX";
                byte[] data = null;
                InputStream is = null;
// Для буферизации текста из потока
                BufferedReader reader = null;
                HttpURLConnection conn = null;
                try {
// Присваиваем путь
                    URL url = new URL("http://10.0.2.2:8000/api/signup/");
                    conn = (HttpURLConnection) url.openConnection();
//Выбираем метод POST для запроса и устанавливаем поля doOutput и doInput
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Content-Type", "application/json");
                    String email = "test3@mail.ru";
                    String data1 = "{\n  \"email\": \""+ email +"\",\n  \"password\": \"1111\"\n}";

                    byte[] out = data1.getBytes(StandardCharsets.UTF_8);

                    OutputStream stream = conn.getOutputStream();
                    stream.write(out);
// Установим тип контента со отправляемыыми значениями
                    //conn.setRequestProperty("Content-Length", "" + Integer.toString(parammetrs.getBytes().length));
                   // OutputStream os = conn.getOutputStream();
                  //  data = parammetrs.getBytes("UTF-8");
                   // os.write(data);
                    data = null;
                    conn.connect();
                    int responseCode = conn.getResponseCode();
// Срабатывает если возвращаемое значение успешное
                    if (responseCode == 200) {
// Полученный результат разбиваем с помощью байтовых потоков
                        is = conn.getInputStream();

                        reader = new BufferedReader(new InputStreamReader(is));

                        StringBuilder buf = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            buf.append(line).append(0);
                        }
                        String[] line1 = (buf.toString()).split("\"");
                        String value = line1[7];
// Возвращаем разбитый по строкам результат
                        return (buf.toString());
                    }
                } catch (MalformedURLException e) {
                    e.getMessage();
                } catch (IOException e) {
                    e.getMessage();
                } catch (Exception e) {
                    e.getMessage();
                }
// Закрываем открытые потоки и подключения
                finally {
                    if (reader != null) {
                        reader.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
