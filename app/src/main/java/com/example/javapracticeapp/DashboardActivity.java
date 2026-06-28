package com.example.javapracticeapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class DashboardActivity extends AppCompatActivity {
    private TextView tvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_dashboard);

       tvWelcome = findViewById(R.id.tv_welcome);
       android.content.Intent incomingIntent = getIntent();

       if(incomingIntent != null && incomingIntent.hasExtra("username")){
           String user = incomingIntent.getStringExtra("username");
           tvWelcome.setText("Welcome back, " + user + "!");
       }
    }
}