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
    DatabaseHelper db;
    Model model;

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
    }

    private void goToAddPage() {
        startActivity(new Intent(this, AddReportActivity.class));
    }

    private void goToRatMap() {
        startActivity(new Intent(this, MapDateRangeActivity.class));
    }

    private void goToSightingsPage() {
        Log.e("HomeActivity", "we made it");
        model = Model.getInstance();
        startActivity(new Intent(this, SightingActivity.class));
    }

    public void showMessage(String title,String Message){
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
            buffer.append("Id :"+ cursor.getString(0)+"\n");
            buffer.append("Created Date :"+ cursor.getString(1)+"\n");
            buffer.append("Location Type :"+ cursor.getString(2)+"\n");
            buffer.append("Zipcode :"+ cursor.getString(3)+"\n");
            buffer.append("Address :"+ cursor.getString(4)+"\n");
            buffer.append("City :"+ cursor.getString(5)+"\n");
            buffer.append("Borrough :"+ cursor.getString(6)+"\n");
            buffer.append("Latitude :"+ cursor.getString(7)+"\n");
            buffer.append("Longitude :"+ cursor.getString(8)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

}
