package com.example.javapracticeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    private TextView tvstatus;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private int defaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvstatus = findViewById(R.id.tv_status);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        defaultTextColor = tvstatus.getCurrentTextColor();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser = etUsername.getText().toString().trim();
                String inputPass = etPassword.getText().toString().trim();

                if(inputUser.equals("") || inputPass.equals("")){
                    tvstatus.setText("Bro Enter Something, Atleast Try");
                    tvstatus.setTextColor(Color.RED);
                    return;
                }
                if(inputUser.equals("admin") && inputPass.equals("1234")){
                    tvstatus.setText("Login Succeful! Welcome.");
                    tvstatus.setTextColor(Color.GREEN);

                    android.content.Intent intent = new android.content.Intent(MainActivity.this, DashboardActivity.class);
                    intent.putExtra("username", inputUser);
                    startActivity(intent);
                    finish();

                } else {
                    tvstatus.setText("Wrong Credentials!");
                    tvstatus.setTextColor(Color.RED);
                }
            }
        });
    }
}