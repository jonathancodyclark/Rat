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
    }

    private void goToAddPage() {
        startActivity(new Intent(this, AddReportActivity.class));
    }

    private void goToSightingsPage() {
        Log.e("HomeActivity", "we made it");
        model = Model.getInstance();
        readCSV();
        startActivity(new Intent(this, SightingActivity.class));
    }

    private void readCSV() {
        int count = 0;
        model.setSightings(); //make the sightings = null before loading in from manually added and CSV file
        List<SightingDataItem> list = model.getSightingsAddedManuallyList();
        for (SightingDataItem sda: list) {
            model.addSighting(sda);
            count++;
        }
        try {
            InputStream is = getResources().openRawResource(R.raw.rat_sightings);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 51) {
                    continue;
                }
                try {
                    if (tokens[0] != null) {
                        model.addSighting(new SightingDataItem(count, Integer.parseInt(tokens[0]), tokens[1], tokens[7], tokens[8], tokens[9],
                        tokens[16], tokens[23], tokens[49], tokens[50]));
                    }

                } catch (Exception e) {
                    Log.wtf("PostLoginActivity", "Error reading data on line" + line, e);
                }
                count++;
            }

            br.close();
        } catch (Exception e) {
            Log.e(TAG, "error reading CSV", e);
        }

    }
}
