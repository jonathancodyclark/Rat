package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.Model.SightingDataItem;
import com.example.jccla.rat.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;

import static com.example.jccla.rat.R.id.bSightingReports;

public class HomeActivity extends AppCompatActivity {
    public static String TAG = "HomeActivity";
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button bSightingReports = (Button) findViewById(R.id.bSightingReports);
        bSightingReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSightingsPage();
            }
        });
        Button bAddSighting = (Button) findViewById(R.id.bAddReport);
        bAddSighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddPage();
            }
        });

        Button bViewMap = (Button) findViewById(R.id.bViewMap);
        bViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRatMap();
            }
        });
    }

    private void goToAddPage() {
        startActivity(new Intent(this, AddReportActivity.class));
    }

    private void goToRatMap() {
        startActivity(new Intent(this, RatMapActivity.class));
    }

    private void goToSightingsPage() {
        Log.e("HomeActivity", "we made it");
        model = Model.getInstance();
        startActivity(new Intent(this, SightingActivity.class));
    }


}
