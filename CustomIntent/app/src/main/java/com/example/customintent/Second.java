package com.example.customintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv = findViewById(R.id.tv_p17b);
        Intent receivedIntent = getIntent();
        if(receivedIntent != null){
            String message = receivedIntent.getStringExtra("message");
            if (message != null)
                tv.setText(message);
            else
                tv.setText("No message received");
        }
    }
}