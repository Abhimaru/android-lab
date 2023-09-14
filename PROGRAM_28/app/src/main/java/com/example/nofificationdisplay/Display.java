package com.example.nofificationdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class Display extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        textView = findViewById(R.id.textView_28);

        Intent intent = getIntent();
        String message = Objects.requireNonNull(intent.getExtras()).getString("notification_message");
        textView.setText(message);
    }
}