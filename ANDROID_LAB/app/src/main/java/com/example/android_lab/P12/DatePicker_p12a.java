package com.example.android_lab.P12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.android_lab.R;

public class DatePicker_p12a extends AppCompatActivity {
    private TextView cur_date;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_p12a);
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