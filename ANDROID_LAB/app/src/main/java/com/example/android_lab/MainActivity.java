package com.example.android_lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_lab.P1.HelloActivity;
import com.example.android_lab.P10.ProgressBarActualFile_p10b;
import com.example.android_lab.P10.ProgressBar_p10a;
import com.example.android_lab.P11.SeekBar_p11;
import com.example.android_lab.P12.DatePicker_p12a;
import com.example.android_lab.P12.TimePicker_p12b;
import com.example.android_lab.P13.ScrollViewHorizontal_p13b;
import com.example.android_lab.P13.ScrollViewVertical_p13a;
import com.example.android_lab.P14.FragmentView_p14;
import com.example.android_lab.P15.OptionMenu_p15;
import com.example.android_lab.P16.ImageSlider_p16b;
import com.example.android_lab.P16.ImageSwitcher_p16a;
import com.example.android_lab.P17.BroadcastReceiver_p17a;
import com.example.android_lab.P17.CustomIntentMain_p17b;
import com.example.android_lab.P18.AlarmManager_p18;
import com.example.android_lab.P19.CallDialer_p19;
import com.example.android_lab.P2.Addition_p2a;
import com.example.android_lab.P2.Calc_p2b;
import com.example.android_lab.P20.SendingSMS_p20;
import com.example.android_lab.P21.SharedPref_p21;
import com.example.android_lab.P3.ItemTotal_p3;
import com.example.android_lab.P4.AutoCompleteText_p4;
import com.example.android_lab.P6.Spinner_p6;
import com.example.android_lab.P7.ListViewMultipleSelect_p7;
import com.example.android_lab.P8.AlertBox_p8;

public class MainActivity extends AppCompatActivity {
    protected ListView list;
    protected Intent intent;

    String[] title = {
            "PROGRAM 1", "PROGRAM 2a", "PROGRAM 2b", "PROGRAM 3", "PROGRAM 4",
            "PROGRAM 6", "PROGRAM 7", "PROGRAM 8", "PROGRAM 9", "PROGRAM 10a", "PROGRAM 10b",
            "PROGRAM 11", "PROGRAM 12a", "PROGRAM 12b","PROGRAM 13a", "PROGRAM 13b", "PROGRAM 14",
            "PROGRAM 15", "PROGRAM 16a", "PROGRAM 16b", "PROGRAM 17a", "PROGRAM 17b", "PROGRAM 18",
            "PROGRAM 19", "PROGRAM 20", "PROGRAM 21"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter adapter = new ListAdapter(this, title);
        list = findViewById(R.id.programList);
        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    intent = new Intent(this, HelloActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(this, Addition_p2a.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(this, Calc_p2b.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(this, ItemTotal_p3.class);
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(this, AutoCompleteText_p4.class);
                    startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(this, Spinner_p6.class);
                    startActivity(intent);
                    break;
                case 6:
                    intent = new Intent(this, ListViewMultipleSelect_p7.class);
                    startActivity(intent);
                    break;
                case 7:
                    intent = new Intent(this, AlertBox_p8.class);
                    startActivity(intent);
                    break;
                case 8:
                    Toast.makeText(getApplicationContext(), "This is custom list View", Toast.LENGTH_LONG).show();
                    break;
                case 9:
                    intent = new Intent(this, ProgressBar_p10a.class);
                    startActivity(intent);
                    break;
                case 10:
                    intent = new Intent(this, ProgressBarActualFile_p10b.class);
                    startActivity(intent);
                    break;
                case 11:
                    intent = new Intent(this, SeekBar_p11.class);
                    startActivity(intent);
                    break;
                case 12:
                    intent = new Intent(this, DatePicker_p12a.class);
                    startActivity(intent);
                    break;
                case 13:
                    intent = new Intent(this, TimePicker_p12b.class);
                    startActivity(intent);
                    break;
                case 14:
                    intent = new Intent(this, ScrollViewVertical_p13a.class);
                    startActivity(intent);
                    break;
                case 15:
                    intent = new Intent(this, ScrollViewHorizontal_p13b.class);
                    startActivity(intent);
                    break;
                case 16:
                    intent = new Intent(this, FragmentView_p14.class);
                    startActivity(intent);
                    break;
                case 17:
                    intent = new Intent(this, OptionMenu_p15.class);
                    startActivity(intent);
                    break;
                case 18:
                    intent = new Intent(this, ImageSwitcher_p16a.class);
                    startActivity(intent);
                    break;
                case 19:
                    intent = new Intent(this, ImageSlider_p16b.class);
                    startActivity(intent);
                    break;
                case 20:
                    intent = new Intent(this, BroadcastReceiver_p17a.class);
                    startActivity(intent);
                    break;
                case 21:
                    intent = new Intent(this, CustomIntentMain_p17b.class);
                    startActivity(intent);
                    break;
                case 22:
                    intent = new Intent(this, AlarmManager_p18.class);
                    startActivity(intent);
                    break;
                case 23:
                    intent = new Intent(this, CallDialer_p19.class);
                    startActivity(intent);
                    break;
                case 24:
                    intent = new Intent(this, SendingSMS_p20.class);
                    startActivity(intent);
                    break;
                case 25:
                    intent = new Intent(this, SharedPref_p21.class);
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}