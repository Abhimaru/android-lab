package com.example.db_demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayCourse extends AppCompatActivity {
    ListView listView;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_course);
        displayListView();
    }
    @Override
    protected void onResume() {
        super.onResume();
        displayListView();
    }
    public void displayListView(){
        listView = findViewById(R.id.lv_courses_p22_23);
        helper = new DBHelper(this);
        ArrayList<CourseModel> courseList = helper.fetchCourses();
        MyCourseAdapter adapter = new MyCourseAdapter(this, courseList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, DetailCourse.class);
            i.putExtra("id", courseList.get(position).id);
            startActivity(i);
        });
    }
}