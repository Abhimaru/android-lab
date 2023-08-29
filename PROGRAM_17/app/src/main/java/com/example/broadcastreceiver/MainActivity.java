package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();
    BluetoothChangeReceiver bluetoothChangeReceiver = new BluetoothChangeReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();

        IntentFilter filter1 = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter1);

        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothChangeReceiver, filter2);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(airplaneModeChangeReceiver);
        unregisterReceiver(bluetoothChangeReceiver);
    }
}