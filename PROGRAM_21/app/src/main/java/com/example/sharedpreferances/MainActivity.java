package com.example.sharedpreferances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button store = findViewById(R.id.btnStore_p21);
        Button display = findViewById(R.id.btnDisplay_p21);
        tvDisplay = findViewById(R.id.tvDisplay_p21);
        prefs = getSharedPreferences("userData", MODE_PRIVATE);

        store.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, getData.class);
            startActivity(intent);
        });

        display.setOnClickListener(v -> {
            displaySharedPreferences();
        });
    }

    private void displaySharedPreferences() {
        String name = prefs.getString("name", "Abhishek");
        String idNo = prefs.getString("id", "21CP315");
        String branch = prefs.getString("branch", "Computer");
        String listPrefs = prefs.getString("language", "1");

        String builder = "Name: " + name + "\n" +
                "ID No: " + idNo + "\n" +
                "Branch: " + branch + "\n" +
                "Language: " + listPrefs + "\n";
        tvDisplay.setText(builder);
    }
}