package com.example.bvmgoolemapsapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity_Bvm extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_bvm);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
        LatLng bvm = new LatLng(22.5521568, 72.9228305);
        googleMap.addMarker(new MarkerOptions().position(bvm).title("Birla Vishwakarma Mahavidyalaya"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(bvm));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bvm, 15f));
    }
}