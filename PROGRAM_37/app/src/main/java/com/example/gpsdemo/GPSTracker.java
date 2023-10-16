package com.example.gpsdemo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class GPSTracker implements LocationListener {
    private final Context context;
    private final MainActivity mainActivity; // Reference to the MainActivity for updating UI

    public GPSTracker(MainActivity activity) {
        this.context = activity;
        this.mainActivity = activity;
    }

    @SuppressLint("MissingPermission")
    public void requestLocationUpdates() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
            } else {
                Toast.makeText(context, "Please enable GPS for accurate location.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Location Manager is not available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        mainActivity.updateLocationText(latitude, longitude); // Update the UI in MainActivity
        mainActivity.setLastLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        mainActivity.displayLastLocation();
    }
}
