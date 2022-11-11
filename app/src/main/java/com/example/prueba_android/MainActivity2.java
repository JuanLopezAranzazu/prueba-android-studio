package com.example.prueba_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textUsername, textPassword;
    Button btnBack;
    EditText textMessage;
    String valueUsername = "", valuePassword = "";
    Paint objPaint;
    PaintRunnable objPaintRunnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnBack = findViewById(R.id.btnBack);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        textMessage = findViewById(R.id.textMessage);

        Bundle bundle = getIntent().getExtras();
        valueUsername = bundle.getString("username");
        valuePassword = bundle.getString("password");
        textUsername.setText("Your username " + valueUsername);
        textPassword.setText("Your password " + valuePassword);

        // async task
        // objPaint = new Paint();
        // objPaint.execute();

        // runnable
        objPaintRunnable = new PaintRunnable();
        Thread hilo = new Thread(objPaintRunnable);
        hilo.start();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigatePage = new Intent(getApplicationContext(), MainActivity.class);
                navigatePage.addFlags(navigatePage.FLAG_ACTIVITY_CLEAR_TASK | navigatePage.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigatePage);
            }
        });
    }

    public int getRandomNumber(){
        return (int)(Math.random() * 255 + 1);
    }

    public class Paint extends AsyncTask<Void,Integer,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            btnBack.setBackgroundColor(Color.rgb(getRandomNumber(), getRandomNumber(), getRandomNumber()));
            btnBack.setText("Back: "+values[0]);
        }
    }

    public class PaintRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                int finalIndex = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnBack.setBackgroundColor(Color.rgb(getRandomNumber(), getRandomNumber(), getRandomNumber()));
                        btnBack.setText("Back: "+ finalIndex);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

