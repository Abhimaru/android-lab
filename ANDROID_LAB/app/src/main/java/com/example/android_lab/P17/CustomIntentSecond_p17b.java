package com.example.android_lab.P17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android_lab.R;

public class CustomIntentSecond_p17b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_intent_second_p17b);
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