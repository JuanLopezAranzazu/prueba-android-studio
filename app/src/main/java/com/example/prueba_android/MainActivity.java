package com.example.prueba_android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSend, btnCreate;
    EditText textUsername, textPassword;
    String array[] = {"Hola mundo", "Hello world"};
    String valueUsername = "", valuePassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueUsername = textUsername.getText().toString();
                valuePassword = textPassword.getText().toString();
                if (valueUsername.length() == 0 || valuePassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Incorrect or missing username or password", Toast.LENGTH_LONG).show();
                } else {
                    Intent navigatePage = new Intent(getApplicationContext(), MainActivity2.class);
                    Bundle dataSend = new Bundle();
                    dataSend.putString("username", valueUsername);
                    dataSend.putString("password", valuePassword);
                    navigatePage.putExtras(dataSend);
                    navigatePage.addFlags(navigatePage.FLAG_ACTIVITY_CLEAR_TASK | navigatePage.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(navigatePage);
                }
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
                Toast.makeText(this, "Activity1", Toast.LENGTH_LONG).show();
                break;

            case R.id.itemActivityMain2:
                navigatePage = new Intent(getApplicationContext(), MainActivity2.class);
                Bundle dataSend = new Bundle();
                navigatePage.putExtras(dataSend);
                navigatePage.addFlags(navigatePage.FLAG_ACTIVITY_CLEAR_TASK | navigatePage.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigatePage);
                // Toast.makeText(this, "Activity2", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", valueUsername);
        outState.putString("password", valuePassword);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        valueUsername = savedInstanceState.getString("username");
        valuePassword = savedInstanceState.getString("password");
    }

    public void createToast(View v) {
        int indexArray = (int)(Math.random() * array.length);
        String valueArray = array[indexArray];
        Toast.makeText(this, valueArray, Toast.LENGTH_LONG).show();
    }

    public void showMessage(View v) {
        if (valueUsername.length() > 0) {
            Toast.makeText(this, valueUsername, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Incorrect or missing username", Toast.LENGTH_LONG).show();
        }
    }
}

