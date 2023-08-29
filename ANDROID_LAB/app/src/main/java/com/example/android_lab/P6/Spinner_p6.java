package com.example.android_lab.P6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android_lab.R;

public class Spinner_p6 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] CITYS = new String[] {
            "Ahmedabad", "Anand", "Baroda", "Bhavnagar", "Bhuj", "Dwarka", "Gandhinagar", "Jamnagar", "Junagadh", "Kutch", "Porbandar", "Rajkot", "Surat", "Vadodara", "Valsad", "Bharuch"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_p6);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CITYS);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(Spinner_p6.this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), CITYS[position] , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}