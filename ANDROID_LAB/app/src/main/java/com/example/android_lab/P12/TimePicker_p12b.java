package com.example.android_lab.P12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android_lab.R;

public class TimePicker_p12b extends AppCompatActivity {
    private TimePicker timePicker;
    private TextView cur_time;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_p12b);
        timePicker = findViewById(R.id.timePicker_p12b);
        timePicker.setIs24HourView(true);
        cur_time = findViewById(R.id.cur_time_p12b);
        btn = findViewById(R.id.button_p12b);

        btn.setOnClickListener(V -> {
            cur_time.setText("Current Time: " + getCurrentTime());
        });
    }

    public String getCurrentTime(){
        return timePicker.getHour() + "h:" + timePicker.getMinute() + "m";
    }
}