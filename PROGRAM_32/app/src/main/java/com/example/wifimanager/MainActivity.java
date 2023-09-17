package com.example.wifimanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WifiManager wifiManager;
    Button bOnOff, bScan;
    TextView tvScanResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        bOnOff = findViewById(R.id.btnTurnOn_p32);
        bScan = findViewById(R.id.btnScan_p32);
        tvScanResults = findViewById(R.id.tvDevices_p32);

        bOnOff.setOnClickListener(v -> enableOrDisableWifi());
        bScan.setOnClickListener(v -> scanDevices());
    }

    private void enableOrDisableWifi() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            Intent wifiSetting = new Intent(Settings.Panel.ACTION_WIFI);
            startActivity(wifiSetting);
        }else{
            wifiManager.setWifiEnabled(true);
        }
    }

    private void scanDevices(){
        wifiManager.startScan();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private final BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> results = wifiManager.getScanResults();

            String sb = "";

            for (ScanResult result : results) {
                sb += "SSID: " + result.SSID + "\n";
//                sb.append("BSSID: ").append(result.BSSID).append("\n");
//                sb.append("RSSI: ").append(result.level).append(" dBm").append("\n\n");
            }

            tvScanResults.setText("Near By Devices:\n" + sb);

            // Unregister the receiver to save battery
            unregisterReceiver(this);
        }
    };
}