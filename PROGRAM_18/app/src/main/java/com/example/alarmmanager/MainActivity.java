package com.example.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected EditText time;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn_p18);
        btn.setOnClickListener(v -> setAlarm());
    }

    private void setAlarm(){
        time = findViewById(R.id.time_p18);
        try {
            int seconds = Integer.parseInt(time.getText().toString().trim());
            if(seconds <= 0){
                Toast.makeText(this, "Time should be more than 0", Toast.LENGTH_SHORT).show();
                return;
            }
            long triggerTime = SystemClock.elapsedRealtime() + seconds * 1000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent);
        } catch (Exception e){
            Toast.makeText(this, "Please enter time", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }

        if (MyBroadcastReceiver.mediaPlayer != null) {
            MyBroadcastReceiver.mediaPlayer.release();
            MyBroadcastReceiver.mediaPlayer = null;
        }
    }
}