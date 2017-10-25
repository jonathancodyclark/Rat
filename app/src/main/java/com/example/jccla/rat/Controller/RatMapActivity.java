package com.example.jccla.rat.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
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

        List<SightingDataItem> sightings = Model.getInstance().getItems();

        int startMonth = getIntent().getIntExtra("START_MONTH", 1);
        int startDay = getIntent().getIntExtra("START_DAY", 1);
        int startYear = getIntent().getIntExtra("START_YEAR", 1);
        int endMonth = getIntent().getIntExtra("END_MONTH", 1);
        int endDay = getIntent().getIntExtra("END_DAY", 1);
        int endYear = getIntent().getIntExtra("END_YEAR", 1);

        for (SightingDataItem s : sightings) {
            if (isDateInRange(s.getDate(), startMonth, startDay, startYear,
                                            endMonth, endDay, endYear)) {
                double lat = Double.parseDouble(s.getLatitude());
                double lng = Double.parseDouble(s.getLongitude());
                LatLng location = new LatLng(lat, lng);

                mMap.addMarker(new MarkerOptions().position(location).title("Reference Key " + s.getKey()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }

        }
    }

    private boolean isDateInRange(String date, int sm, int sd, int sy, int em, int ed, int ey) {

        //given date format example: 9/14/2015  12:00:00 AM
        String onlyDate = date.substring(0, 10);     //cut off time part
        String[] dateInts = onlyDate.split("/");
        int month = Integer.parseInt(dateInts[0]);
        int day = Integer.parseInt(dateInts[1]);
        int year = Integer.parseInt(dateInts[2]);

        if (year < sy || year > ey) {
            return false;
        } else if (year == sy && month < sm) {
            return false;
        } else if (year == sy && month == sm && day < sd) {
            return false;
        } else if (year == ey && month > em) {
            return false;
        } else if (year == ey && month == em && day > ed) {
            return false;
        } else {
            return true;
        }
    }
}
