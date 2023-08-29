package com.example.customelistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected ListView list;

    String[] title = {
            "PROGRAM 1", "PROGRAM 2", "PROGRAM 3", "PROGRAM 4", "PROGRAM 5",
            "PROGRAM 6", "PROGRAM 7", "PROGRAM 8", "PROGRAM 9", "PROGRAM 10",
            "PROGRAM 11", "PROGRAM 12", "PROGRAM 13", "PROGRAM 14", "PROGRAM 15",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListAdapter adapter = new MyListAdapter(this, title);
        list = findViewById(R.id.myListView);
        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(
                MainActivity.this,
                "You clicked: " + title[position],
                Toast.LENGTH_SHORT
        ).show());

    }
}