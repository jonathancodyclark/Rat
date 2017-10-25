package com.example.jccla.rat.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jccla.rat.R;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.Model.SightingDataItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class RatMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_map);
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        List<SightingDataItem> sightings = Model.getInstance().getItems();
        if (sightings.size() == 0) {
            Toast.makeText(this,"You must load data before showing map",Toast.LENGTH_LONG).show();
            return;
        }
        for (int i = 0; i < 100; i++) {
            double lat = Double.parseDouble(sightings.get(i).getLatitude());
            double lng = Double.parseDouble(sightings.get(i).getLongitude());
            LatLng location = new LatLng(lat, lng);

            mMap.addMarker(new MarkerOptions().position(location).title("New Sighting"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
}