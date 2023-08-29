package com.example.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    protected static MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();
            mediaPlayer = null;
        });
        mediaPlayer.start();
        Toast.makeText(context, "Alarm is Ringing...", Toast.LENGTH_LONG).show();
    }
}
