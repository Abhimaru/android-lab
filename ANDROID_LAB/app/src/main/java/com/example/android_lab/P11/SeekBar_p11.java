package com.example.android_lab.P11;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_lab.R;

public class SeekBar_p11 extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_p11);

        ratingBar = findViewById(R.id.ratingBar_p11);
        Button btn = findViewById(R.id.submitBtn_p11);

        btn.setOnClickListener(v -> {
            String rating = String.valueOf(ratingBar.getRating());
            Toast.makeText(this, rating, Toast.LENGTH_SHORT).show();
        });
    }
}