package com.example.gpsdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private TextView latitudeTextView, longitudeTextView;
    private GPSTracker gpsTracker;
    private Location lastLocation; // Track the last known location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getLocationButton = findViewById(R.id.getLocationButton);
        latitudeTextView = findViewById(R.id.latitudeTextView);
        longitudeTextView = findViewById(R.id.longitudeTextView);

        displayLastLocation();
        gpsTracker = new GPSTracker(this);

        getLocationButton.setOnClickListener(view -> {
            if (checkLocationPermission()) {
                gpsTracker.requestLocationUpdates();
            }
        });
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gpsTracker.requestLocationUpdates();
            }
        }
    }

    // Update the UI with the new location
    @SuppressLint("SetTextI18n")
    public void updateLocationText(double latitude, double longitude) {
        latitudeTextView.setText("Latitude: " + latitude);
        longitudeTextView.setText("Longitude: " + longitude);
    }

    @SuppressLint("SetTextI18n")
    public void displayLastLocation() {
        if (lastLocation != null) {
            double latitude = lastLocation.getLatitude();
            double longitude = lastLocation.getLongitude();
            updateLocationText(latitude, longitude);
        } else {
            latitudeTextView.setText("Last Known Latitude: N/A");
            longitudeTextView.setText("Last Known Longitude: N/A");
        }
    }

    public void setLastLocation(Location location) {
        lastLocation = location;
    }
}
