package com.example.android_lab.P20;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.android_lab.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendingSMS_p20 extends AppCompatActivity {
    protected EditText mobileNum, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_sms_p20);
        mobileNum = findViewById(R.id.mobilenum_p20);
        message = findViewById(R.id.msg_p20);
        Button btn = findViewById(R.id.sendbtn_p20);
        btn.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                sendSMSMessage();
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},100);
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
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        String mno = mobileNum.getText().toString().trim();
        String msg = message.getText().toString().trim();

        Matcher m = p.matcher(mno);
        if (!mno.isEmpty() && !msg.isEmpty()) {
            if(m.matches()){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mno, null, msg, null, null);
                Toast.makeText(this, "Message Successfully Sent...!" , Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "SMS Failed...", Toast.LENGTH_SHORT).show();
        }
    }
}