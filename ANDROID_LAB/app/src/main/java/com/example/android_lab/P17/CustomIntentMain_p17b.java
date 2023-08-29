package com.example.android_lab.P17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.android_lab.R;

public class CustomIntentMain_p17b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_intent_main_p17b);
        Button btn = findViewById(R.id.btn_p17b);
        btn.setOnClickListener(v -> sendCustomIntent());
    }

    private void sendCustomIntent(){
        Intent customIntent = new Intent("com.example.android_lab.P17.CUSTOM_ACTION");
        customIntent.putExtra("message", "Hello from custom intent");
        startActivity(customIntent);
    }
}