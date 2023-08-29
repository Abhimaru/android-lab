package com.example.seekbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar_p11);
        Button btn = findViewById(R.id.submitBtn_p11);

        btn.setOnClickListener(v -> {
            String rating = String.valueOf(ratingBar.getRating());
            Toast.makeText(MainActivity.this, rating, Toast.LENGTH_SHORT).show();
        });
    }
}