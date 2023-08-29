package com.example.sharedpreferances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class getData extends AppCompatActivity {
    EditText etName, etId, etBranch;
    Spinner spLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
            etName = findViewById(R.id.etName_p21);
            etId = findViewById(R.id.etId_p21);
            etBranch = findViewById(R.id.etBranch_p21);
            spLanguage = findViewById(R.id.spLanguage_p21);

        findViewById(R.id.btnSave_p21).setOnClickListener(v -> {
            String name = etName.getText().toString();
            String id = etId.getText().toString();
            String branch = etBranch.getText().toString();
            String language = spLanguage.getSelectedItem().toString();

            SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", name);
            editor.putString("id", id);
            editor.putString("branch", branch);
            editor.putString("language", language);
            editor.apply();

            finish();
        });
    }
}