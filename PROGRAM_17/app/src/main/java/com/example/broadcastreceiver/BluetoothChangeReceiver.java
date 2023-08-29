package com.example.broadcastreceiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BluetoothChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

            if (state == BluetoothAdapter.STATE_ON) {
                Toast.makeText(context, "Bluetooth is now On", Toast.LENGTH_SHORT).show();
            }

            if (state == BluetoothAdapter.STATE_OFF) {
                Toast.makeText(context, "Bluetooth is now Off", Toast.LENGTH_SHORT).show();
            }

            if (state == BluetoothAdapter.STATE_TURNING_ON) {
                Toast.makeText(context, "Bluetooth is Turning on...", Toast.LENGTH_SHORT).show();
            }

            if (state == BluetoothAdapter.STATE_TURNING_OFF) {
                Toast.makeText(context, "Bluetooth is Turning Off...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
