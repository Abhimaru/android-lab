package com.example.mailsender;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendEmailButton = findViewById(R.id.btn_p30);
        sendEmailButton.setOnClickListener(v -> sendEmail());
    }
    @SuppressLint("QueryPermissionsNeeded")
    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"21cp315@bvmengineering.ac.in"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Good Morning");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, How are you?");

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}