package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.R;

public class HomeActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DatabaseHelper(this);
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
        Button viewAddedReports = (Button) findViewById(R.id.view_added_reports);
        viewAddedReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRatReports();
            }
        });
        Button bGraph = (Button) findViewById(R.id.bGraph);
        bGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGraph();
            }
        });
    }

    private void goToAddPage() {
        startActivity(new Intent(this, AddReportActivity.class));
    }

    private void goToRatMap() {
        startActivity(new Intent(this, MapDateRangeActivity.class));
    }

    private void goToSightingsPage() {
        Log.e("HomeActivity", "we made it");
        startActivity(new Intent(this, SightingActivity.class));
    }
    private void goToGraph() { startActivity(new Intent(this, GraphDateRangeActivity.class)); }

    private void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void viewRatReports() {
        Cursor cursor = db.getAllRatData();
        if(cursor.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("Id :").append(cursor.getString(0)).append("\n");
            buffer.append("Created Date :").append(cursor.getString(1)).append("\n");
            buffer.append("Location Type :").append(cursor.getString(2)).append("\n");
            buffer.append("Zip code :").append(cursor.getString(3)).append("\n");
            buffer.append("Address :").append(cursor.getString(4)).append("\n");
            buffer.append("City :").append(cursor.getString(5)).append("\n");
            buffer.append("Borough :").append(cursor.getString(6)).append("\n");
            buffer.append("Latitude :").append(cursor.getString(7)).append("\n");
            buffer.append("Longitude :").append(cursor.getString(8)).append("\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

}
