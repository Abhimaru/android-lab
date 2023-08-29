package com.example.alertbox;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            alert.show();
        });
    }
}