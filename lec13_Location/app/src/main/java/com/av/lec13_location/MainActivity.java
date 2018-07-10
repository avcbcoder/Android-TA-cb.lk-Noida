package com.av.lec13_location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    public static final String TAG = "MainActivity";
    public static final int REQUEST_LOCATION_PERMISSION = 123;
    LocationManager locationManager;
    TextView tv1, tv2, tv3, tv4, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);

        // checkSelfPermission return 0 if we have permission, 1 otherwise
        // use contextCompact.checkSel... instead of checkselefper,..
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            // This block will execute when we dont have either of the permission
            // It is async and runs on different thread, it will not block further execution in OnCreate
            ActivityCompat.requestPermissions(this,
                    // All that we are requesting here should must be registered in manifest
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION
            );
        } else {
            // Add location permission for location :location fine,coarse
            // After giving this permission error still persist,
            // to remove this hack is to reduce targetSdk
            // or we can add Permissions in beginning
            // Now for uploading on play store, target sdk should be (current max SDK)-1 , so hack fails
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            addLocationListeners();
        }
    }

    @SuppressLint("MissingPermission") // since we have not checked , so warning will be shown
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            // called after permission dialog disappears
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // We have got the permission
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                addLocationListeners();
            } else {
                Toast.makeText(this, "This permission needed", Toast.LENGTH_SHORT).show();
            }
        } else {
            // some other requests
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged: Latitude " + location.getLatitude());
        Log.e(TAG, "onLocationChanged: Longitude " + location.getLongitude());
        Log.e(TAG, "onLocationChanged: Provider " + location.getProvider());
        Log.e(TAG, "onLocationChanged: Time " + location.getTime());
        //Only GPS provider will give the below value
        Log.e(TAG, "onLocationChanged: Speed " + location.getSpeed());
        Log.e(TAG, "onLocationChanged: Accuracy " + location.getAccuracy());
        Log.e(TAG, "onLocationChanged: Altitude " + location.getAltitude());
        tv1.setText("Latitude " + location.getLatitude());
        tv2.setText("Longitude " + location.getLongitude());
        tv3.setText("Provide " + location.getProvider());
        tv4.setText("Time " + location.getTime());
        tv5.setText("Speed " + location.getSpeed());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @SuppressLint("MissingPermission")
    public void addLocationListeners() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000,// It will not work in case of services
                0,
                this);
    }
}
