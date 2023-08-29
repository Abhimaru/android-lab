package com.example.datepicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView cur_date;
    private DatePicker datePicker;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cur_date = findViewById(R.id.cur_date_p12a);
        datePicker = findViewById(R.id.datePicker_p12a);
        Button btn = findViewById(R.id.button_p12a);

        btn.setOnClickListener(v -> cur_date.setText("Current Date: "+getCurrentDate()));
    }

    public String getCurrentDate(){
        return datePicker.getDayOfMonth() + "/" +
                (datePicker.getMonth() + 1) + "/" +
                datePicker.getYear();
    }
}