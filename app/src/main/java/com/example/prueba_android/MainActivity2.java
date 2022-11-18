package com.example.prueba_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView textUsername, textPassword;
    Button btnBack;
    EditText textMessage;
    String valueUsername = "", valuePassword = "";
    Paint objPaint;
    PaintRunnable objPaintRunnable;
    Handler handler;


    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnBack = findViewById(R.id.btnBack);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        textMessage = findViewById(R.id.textMessage);

        listView = findViewById(R.id.list);
        ArrayAdapter<String> listValues;
        listValues = new ArrayAdapter<String>(this, android.support.constraint.R.layout.support_simple_spinner_dropdown_item, values);
        listView.setAdapter(listValues);

        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            valueUsername = bundle.getString("username");
            valuePassword = bundle.getString("password");
            textUsername.setText("Your username " + valueUsername);
            textPassword.setText("Your password " + valuePassword);
        } else {
            textUsername.setText("Has no username");
            textPassword.setText("Has no password");
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent navigatePage;
        switch (item.getItemId()) {
            case R.id.itemActivityMain1:
                navigatePage = new Intent(getApplicationContext(), MainActivity.class);
                navigatePage.addFlags(navigatePage.FLAG_ACTIVITY_CLEAR_TASK | navigatePage.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigatePage);
                // Toast.makeText(this, "Activity1", Toast.LENGTH_LONG).show();
                break;

            case R.id.itemActivityMain2:
                Toast.makeText(this, "Activity2", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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

