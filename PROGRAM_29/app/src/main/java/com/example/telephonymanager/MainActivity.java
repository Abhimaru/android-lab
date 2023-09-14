package com.example.telephonymanager;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView_29);

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        String networkCountryISO = tm.getNetworkCountryIso();
        String SIMCountryISO = tm.getSimCountryIso();

        String strPhoneType="";
        int phoneType=tm.getPhoneType();

        switch (phoneType)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strPhoneType="CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strPhoneType="GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strPhoneType="NONE";
                break;
        }

        boolean isRoaming=tm.isNetworkRoaming();

        String info="Phone Details:\n";
        info+="\nNetwork Country ISO: "+networkCountryISO;
        info+="\nSIM Country ISO: "+SIMCountryISO;
        info+="\nPhone Network Type: "+strPhoneType;
        info+="\nIn Roaming?: "+isRoaming;

        tv.setText(info);
    }
}