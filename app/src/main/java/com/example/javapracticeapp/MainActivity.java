package com.example.javapracticeapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity{
    private TextView tvstatus;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private int defaultTextColor;

    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvstatus = findViewById(R.id.tv_status);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        defaultTextColor = tvstatus.getCurrentTextColor();

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString().trim();
                String passowrd = etPassword.getText().toString().trim();

                if(email.equals("") || passowrd.equals("")){
                    tvstatus.setText("Bro Enter Something, Atleast Try");
                    tvstatus.setTextColor(Color.RED);
                    return;
                }
                tvstatus.setText("Checking Credentials Online...");
                tvstatus.setTextColor(Color.BLUE);

                mAuth.signInWithEmailAndPassword(email,passowrd).addOnCompleteListener(MainActivity.this, task -> {
                    if(task.isSuccessful()){
                        tvstatus.setText("Login Successful!");
                        tvstatus.setTextColor(Color.GREEN);

                        FirebaseUser user = mAuth.getCurrentUser();
                        String userEmail = (user != null) ? user.getEmail() : "Developer";

                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        intent.putExtra("username", userEmail);
                        startActivity(intent);
                        finish();
                    } else{
                        tvstatus.setText("Authentication Failed:" + task.getException().getMessage());
                        tvstatus.setTextColor(Color.RED);
                    }
                });
            }
        });

        // Inside onCreate of MainActivity.java:
        TextView tvGoRegister = findViewById(R.id.tv_go_to_register);
        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                // Note: Do NOT call finish() here, because if they press back, they should return to login!
            }
        });
    }
}