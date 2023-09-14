package com.example.nofificationdisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_NAME = "personal_notifications";
    private static final int NOTIFICATION_ID = 100;
    private static final String CHANNEL_ID = "personal_notifications";
    Button btnShowNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowNotification = findViewById(R.id.btn_28);
        btnShowNotification.setOnClickListener(v -> displayNotification());
    }

    public void displayNotification(){
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;

        notification = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_360_24)
                .setContentTitle("Demo Notification")
                .setContentText("This is demo notification")
                .setColor(getResources().getColor(R.color.red, null))
                .setChannelId(CHANNEL_NAME)
                .build();

        Intent intent = new Intent(this, Display.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("notification_message", "Check notification bar");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH));
        nm.notify(NOTIFICATION_ID, notification);

        notification.contentIntent = pendingIntent;
        startActivity(intent);
    }
}