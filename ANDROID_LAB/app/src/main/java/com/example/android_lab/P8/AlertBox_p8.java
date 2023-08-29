package com.example.android_lab.P8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_lab.R;

public class AlertBox_p8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_box_p8);

        Button showAlertBtn = findViewById(R.id.buttonShowAlert_p8);

        showAlertBtn.setOnClickListener(v -> {
            final CharSequence[] options = {"Android", "Java", "Python", "C++"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose an option:");
            builder.setSingleChoiceItems(options, -1, (dialog, item) -> {
                String selectedOption = options[item].toString();
                Toast.makeText(this, selectedOption, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });

            AlertDialog alert = builder.create();
            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(155, 232, 216)));
            alert.show();
        });
    }
}