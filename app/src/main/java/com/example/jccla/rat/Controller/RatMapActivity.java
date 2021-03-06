package com.example.jccla.rat.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

        int startMonth = getIntent().getIntExtra("START_MONTH", 1);
        int startDay = getIntent().getIntExtra("START_DAY", 1);
        int startYear = getIntent().getIntExtra("START_YEAR", 1);
        int endMonth = getIntent().getIntExtra("END_MONTH", 1);
        int endDay = getIntent().getIntExtra("END_DAY", 1);
        int endYear = getIntent().getIntExtra("END_YEAR", 1);

        List<SightingDataItem> sightingsInRange = Model.getInstance().getSightingsInRange(startMonth, startDay, startYear,
                                                                            endMonth, endDay, endYear);

        for (SightingDataItem s : sightingsInRange) {

            double lat = Double.parseDouble(s.getLatitude());
            double lng = Double.parseDouble(s.getLongitude());
            LatLng location = new LatLng(lat, lng);

            googleMap.addMarker(new MarkerOptions().position(location).title("Reference Key " + s.getKey()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        }
    }


}
