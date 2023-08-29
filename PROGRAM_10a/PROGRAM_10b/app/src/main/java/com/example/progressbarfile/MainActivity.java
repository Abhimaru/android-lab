package com.example.progressbarfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    private TextView filename;
    private Button btn;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filename = findViewById(R.id.fileName_p10b);
        btn = findViewById(R.id.downloadBtn_p10b);
        progress = findViewById(R.id.progressBar_p10b);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dawloadFile();
            }
        });
    }

    private void dawloadFile() {
        btn.setEnabled(false);
        progress.setVisibility(View.VISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                btn.setEnabled(true);
                progress.setVisibility(View.GONE);
                openDownloadFile();
                filename.setText("file.txt");
            }
        }, 5000);
    }

    private void openDownloadFile() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            copySampleFile();
        }
    }

}