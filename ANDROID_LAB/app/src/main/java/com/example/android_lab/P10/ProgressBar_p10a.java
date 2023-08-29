package com.example.android_lab.P10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android_lab.R;

public class ProgressBar_p10a extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private int progressStatus = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_p10a);
        Button startButton = findViewById(R.id.startBtn_P10a);
        progressBar = findViewById(R.id.progressBar_p10a);
        progressText = findViewById(R.id.progressText_P10a);
        startButton.setOnClickListener(v -> startProgress());
    }

    @SuppressWarnings("BusyWait")
    @SuppressLint("SetTextI18n")
    private void startProgress() {
        findViewById(R.id.startBtn_P10a).setClickable(false);
        progressStatus = 0;
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(progressStatus);
        progressText.setText("");

        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                handler.post(() -> {
                    progressBar.setProgress(progressStatus);

                    if (progressStatus == 100) {
                        progressText.setText("Progress Completed");
                        progressBar.setVisibility(View.GONE);
                        findViewById(R.id.startBtn_P10a).setClickable(true);
                    } else {
                        progressText.setText(progressStatus + "/" + progressBar.getMax() );
                    }
                });
                try {
                    Thread.sleep(1000); // Update progress every one second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}