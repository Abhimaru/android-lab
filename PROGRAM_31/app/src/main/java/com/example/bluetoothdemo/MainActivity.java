package com.example.bluetoothdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView tvPairedDevices;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_DISCOVERABLE_BT = 2;
    private static final int REQUEST_DISCOVERABLE_DURATION = 300;

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        tvPairedDevices = findViewById(R.id.tvDevices_p30);
        Button bOn = findViewById(R.id.btnTurnOn_p30);
        Button bOff = findViewById(R.id.btnTurnOff_p30);
        Button bDiscoverable = findViewById(R.id.btnDiscoverable_p30);
        Button bPairedDevices = findViewById(R.id.btnPairedDevices_p30);

        bOn.setOnClickListener(v -> enableBluetooth());
        bOff.setOnClickListener(v -> disableBluetooth());
        bDiscoverable.setOnClickListener(v -> makeDiscoverable());
        bPairedDevices.setOnClickListener(v -> displayPairedDevices());
    }

    private void enableBluetooth() {
        if (bluetoothAdapter == null) {
            System.out.println("Bluetooth not supported");
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            Toast.makeText(this, "Turning On Bluetooth", Toast.LENGTH_SHORT).show();
            startActivityForResult(turnOn, REQUEST_ENABLE_BT);
        }
    }

    private void disableBluetooth() {
        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Turned Off Bluetooth", Toast.LENGTH_SHORT).show();
            bluetoothAdapter.disable();
        }
    }

    private void makeDiscoverable() {
        if (!bluetoothAdapter.isDiscovering()) {
            Intent discoverable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, REQUEST_DISCOVERABLE_DURATION);
            Toast.makeText(this, "Discoverable for " + REQUEST_DISCOVERABLE_DURATION + " seconds", Toast.LENGTH_SHORT).show();
            startActivityForResult(discoverable, REQUEST_DISCOVERABLE_BT);
        }
    }

    private void displayPairedDevices() {
        // Get the paired devices
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices != null && !pairedDevices.isEmpty()) {
            StringBuilder devicesText = new StringBuilder("Paired Devices:\n");
            for (BluetoothDevice device : pairedDevices) {
                devicesText.append(device.getName()).append("\n");
            }
            tvPairedDevices.setText(devicesText.toString());
        } else {
            tvPairedDevices.setText("No paired devices found.");
        }
    }

    }