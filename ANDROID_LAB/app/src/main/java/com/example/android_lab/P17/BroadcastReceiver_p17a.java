package com.example.android_lab.P17;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.android_lab.R;

public class BroadcastReceiver_p17a extends AppCompatActivity {
    AirplaneModeChangeReceiver_p17a airplaneModeChangeReceiver = new AirplaneModeChangeReceiver_p17a();
    BluetoothChangeReceiver_p17a bluetoothChangeReceiver = new BluetoothChangeReceiver_p17a();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver_p17a);
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