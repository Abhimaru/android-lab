package com.example.customintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn_p17b);
        btn.setOnClickListener(v -> sendCustomIntent());
    }

    private void sendCustomIntent(){
        Intent customIntent = new Intent("com.example.customintent.CUSTOM_ACTION");
        customIntent.putExtra("message", "Hello from custom intent");
        startActivity(customIntent);
    }
}