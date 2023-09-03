package com.example.db_demo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailCourse extends AppCompatActivity {
    TextView tvName, tvDuration, tvTracks, tvDescription;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        tvName = findViewById(R.id.tv_c_name_p22_23);
        tvDuration = findViewById(R.id.tv_c_duration_p22_23);
        tvTracks = findViewById(R.id.tv_c_tracks_p22_23);
        tvDescription = findViewById(R.id.tv_c_description_p22_23);

        helper = new DBHelper(this);
        int id = getIntent().getIntExtra("id", 0);
        CourseModel course = helper.fetchCourseById(id);
        tvName.setText(course.name);
        tvDuration.setText(String.format("Duration: %s", course.duration));
        tvTracks.setText(course.tracks);
        tvDescription.setText(course.description);


        Button backBtn = findViewById(R.id.backBtn_p22_23);
        backBtn.setOnClickListener(v -> finish());

        Button deleteBtn = findViewById(R.id.delBtn_p22_23);
        deleteBtn.setOnClickListener(v -> {
            helper = new DBHelper(this);
            helper.deleteCourseById(id);
            Toast.makeText(this, "Course deleted successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}