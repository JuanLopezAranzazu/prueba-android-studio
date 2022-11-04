package com.example.prueba_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textUsername, textPassword;
    Button btnBack;
    String valueUsername = "", valuePassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnBack = findViewById(R.id.btnBack);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);

        Bundle bundle = getIntent().getExtras();
        valueUsername = bundle.getString("username");
        valuePassword = bundle.getString("password");
        textUsername.setText("Your username " + valueUsername);
        textPassword.setText("Your password " + valuePassword);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigatePage = new Intent(getApplicationContext(), MainActivity.class);
                navigatePage.addFlags(navigatePage.FLAG_ACTIVITY_CLEAR_TASK | navigatePage.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigatePage);
            }
        });
    }
}