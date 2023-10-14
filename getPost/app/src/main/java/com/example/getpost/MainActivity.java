package com.example.getpost;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    TextView tvStatus, tvRole, tvMethod;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvStatus = findViewById(R.id.statusField);
        tvRole = findViewById(R.id.roleField);
        tvMethod = findViewById(R.id.methodField);
        Button btnPost = findViewById(R.id.postMethod);
        Button btnGet = findViewById(R.id.getMethod);

        btnPost.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            tvMethod.setText("METHOD: POST");
            new SignInActivity(tvStatus, tvRole, 1).execute(username, password);
        });

        btnGet.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            tvMethod.setText("Method: GET");
            new SignInActivity(tvStatus, tvRole, 0).execute(username, password);
        });
    }
}