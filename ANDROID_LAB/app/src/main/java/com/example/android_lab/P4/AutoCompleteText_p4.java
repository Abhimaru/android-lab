package com.example.android_lab.P4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.android_lab.R;

public class AutoCompleteText_p4 extends AppCompatActivity {

    protected AutoCompleteTextView text;
    private static final String[] CITYS = new String[] {
            "Ahmedabad", "Anand", "Baroda", "Bhavnagar", "Bhuj", "Dwarka", "Gandhinagar", "Jamnagar", "Junagadh", "Kutch", "Porbandar", "Rajkot", "Surat", "Vadodara", "Valsad", "Bharuch"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_p4);
        text = findViewById(R.id.cityname_p4);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, CITYS);
        text.setAdapter(adapter);
    }
}