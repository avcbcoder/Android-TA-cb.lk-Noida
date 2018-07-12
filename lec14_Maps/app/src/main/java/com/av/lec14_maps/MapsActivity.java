package com.av.lec14_maps;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng lastLocation = null;
    ArrayList<LatLng> allPos = new ArrayList<>();
    String[] colors = {"#3467ba", "#ff2600", "#ffd000", "#00ff50", "#00edff", "#ff00ee", "#ff0054"};
    Random r = new Random();
    int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //-------styling
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style));

            if (!success) {
                Log.e("-------", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("--------", "Can't find style. Error: ", e);
        }
        // Position the map's camera near Sydney, Australia.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
        //--------------


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
//        MarkerOptions markerOptions = new MarkerOptions()
//                .draggable(true)
//                .position(sydney).title("");

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.0f));

        LatLng delhi = new LatLng(28.35, 62);
        LatLng antarctica = new LatLng(82.86, 135);

        final PolylineOptions polylineOptions = new PolylineOptions().add(sydney, delhi, antarctica);
        PolygonOptions polygonOptions = new PolygonOptions().add(sydney, delhi, antarctica);
        polygonOptions.fillColor(R.color.colorAccent);
        CircleOptions circleOptions = new CircleOptions().center(sydney).radius(10000);

        //mMap.addPolyline(polylineOptions);
        mMap.addPolygon(polygonOptions);
        mMap.addCircle(circleOptions);

        lastLocation = sydney;
        allPos.add(lastLocation);
        // Two types of listeners of Marker: OnDrag, OnClick
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {// called when dragging starts
                //LatLng newLatLng = marker.getPosition();// There will be some shift if we will use this
                // For getting the name of place of latlng, use google place API
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(lastLocation)
                        .title(marker.getId());
                mMap.addMarker(markerOptions);
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                // called when dragging ends
//                PolylineOptions polylineOptions1=new PolylineOptions().add(lastLocation,marker.getPosition());
//                mMap.addPolyline(new PolylineOptions().add(lastLocation, marker.getPosition()));
                lastLocation = marker.getPosition();
                for (LatLng pos : allPos)
                    mMap.addPolyline(new PolylineOptions().color(Color.parseColor(colors[c])).add(pos, marker.getPosition()));
                allPos.add(marker.getPosition());
                c++;
                c%=colors.length;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 10.0f));
            }
        });
    }
}
