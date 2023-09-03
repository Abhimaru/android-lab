package com.example.db_demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etDuration, etTracks, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insertBtn = findViewById(R.id.btn_insert_p22_23);
        Button displayBtn = findViewById(R.id.btn_display_p22_23);
        etName = findViewById(R.id.et_name_p22_23);
        etDuration = findViewById(R.id.et_duration_p22_23);
        etTracks = findViewById(R.id.et_tracks_p22_23);
        etDescription = findViewById(R.id.et_description_p22_23);

        insertBtn.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String duration = etDuration.getText().toString();
            String tracks = etTracks.getText().toString();
            String description = etDescription.getText().toString();
            DBHelper dbHelper = new DBHelper(this);
            if(name.isEmpty() || duration.isEmpty() || tracks.isEmpty() || description.isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHelper.addCourse(name, duration, tracks, description, this);
            clearText();
            dbHelper.close();
        });

        displayBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, DisplayCourse.class);
            startActivity(i);
        });
    }

    public void clearText(){
        etName.setText("");
        etDuration.setText("");
        etTracks.setText("");
        etDescription.setText("");
    }
}