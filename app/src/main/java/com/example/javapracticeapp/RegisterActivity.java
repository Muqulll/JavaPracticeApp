package com.example.javapracticeapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvStatus;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvStatus = findViewById(R.id.tv_register_status);
        etEmail = findViewById(R.id.et_register_email);
        etPassword = findViewById(R.id.et_register_password);
        btnRegister = findViewById(R.id.btn_register_submit);

        // Crucial Firebase initialization step!
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.equals("") || password.equals("")) {
                    tvStatus.setText("Fill in all fields, bro.");
                    tvStatus.setTextColor(Color.RED);
                    return;
                }

                tvStatus.setText("Creating account online...");
                tvStatus.setTextColor(Color.WHITE);

                // Fire the sign-up network request down to Firebase Cloud
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, task -> {
                            if (task.isSuccessful()) {
                                tvStatus.setText("Account Created successfully!");
                                tvStatus.setTextColor(Color.GREEN);

                                // Bounce them directly onto the dashboard screen
                                Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                                intent.putExtra("username", email);
                                startActivity(intent);
                                finish();
                            } else {
                                tvStatus.setText("Signup Failed: " + task.getException().getMessage());
                                tvStatus.setTextColor(Color.RED);
                            }
                        });
            }
        });
    }
}