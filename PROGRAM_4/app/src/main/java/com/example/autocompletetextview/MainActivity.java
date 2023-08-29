package com.example.autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    protected AutoCompleteTextView text;
    private static final String[] CITYS = new String[] {
            "Ahmedabad", "Anand", "Baroda", "Bhavnagar", "Bhuj", "Dwarka", "Gandhinagar", "Jamnagar", "Junagadh", "Kutch", "Porbandar", "Rajkot", "Surat", "Vadodara", "Valsad", "Bharuch"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.cityname);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, CITYS);
        text.setAdapter(adapter);
    }
}