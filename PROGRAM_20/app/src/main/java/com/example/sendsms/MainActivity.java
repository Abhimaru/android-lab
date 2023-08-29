package com.example.sendsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    protected EditText mobileNum, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobileNum = findViewById(R.id.mobilenum_p20);
        message = findViewById(R.id.msg_p20);
        Button btn = findViewById(R.id.btn_p20);
        btn.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                sendSMSMessage();
            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},100);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 & grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMSMessage();
        }
        else{
            Toast.makeText(this, "Permission is not Granted", Toast.LENGTH_SHORT).show();
        }
    }
    protected void sendSMSMessage(){
        String mno = mobileNum.getText().toString().trim();
        String msg = message.getText().toString().trim();

        if (!mno.isEmpty() && !msg.isEmpty()) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mno, null, msg, null, null);
            Toast.makeText(this, "Message Successfully Sent...!" , Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "SMS Failed...", Toast.LENGTH_SHORT).show();
        }
    }
}