package com.example.bvmgoolemapsapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bvm = findViewById(R.id.btn_bvm_location);
        Button current = findViewById(R.id.btn_current_location);

        bvm.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MapsActivity_Bvm.class);
            startActivity(i);
        });

        current.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MapsActivity_Current.class);
            startActivity(i);
        });
    }
}