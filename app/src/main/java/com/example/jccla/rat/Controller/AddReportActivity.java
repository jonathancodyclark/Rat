package com.example.jccla.rat.Controller;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.R;

import java.util.Random;

public class AddReportActivity extends AppCompatActivity {
    private EditText etDateAndTime;
    private EditText etLocationType;
    private EditText etZipCode;
    private EditText etAddress;
    private EditText etCity;
    private EditText etBorough;
    private EditText etLat;
    private EditText etLong;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        //db = new RepDatabaseHelper(this);
        db = new DatabaseHelper(this);
        etDateAndTime = (EditText) findViewById(R.id.etDate);
        etLocationType = (EditText) findViewById(R.id.etLocationType);
        etZipCode = (EditText) findViewById(R.id.etZip);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etCity = (EditText) findViewById(R.id.etCity);
        etBorough = (EditText) findViewById(R.id.etBorough);
        etLat= (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);
        CircularProgressButton bSubmit = (CircularProgressButton) findViewById(R.id.bAdd_Submit);
        CircularProgressButton bCancel = (CircularProgressButton) findViewById(R.id.bAdd_Cancel);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAndGoToHomePage();
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomePage();
            }
        });
    }

    private void goToHomePage() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    private final Random rand = new Random();
    private final int  n = rand.nextInt(31464015) + 1; //31464015 is the first key (or ID) of the rat reports in the CSV

    private void submitAndGoToHomePage() {
        boolean inserted = db.insertRatData((n+ ""),  etDateAndTime.getText().toString(), etLocationType.getText().toString(), etZipCode.getText().toString(), etAddress.getText().toString(),
                etCity.getText().toString(), etBorough.getText().toString(), etLat.getText().toString(),  etLong.getText().toString());
        if(inserted) {
            Toast.makeText(this, "Data Inserted, Registered", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
        }
        else {
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }

//        model.putInLinkedList(
//                n,
//                etDateAndTime.getText().toString(),
//                etLocationType.getText().toString(),
//                etZipCode.getText().toString(),
//                etAddress.getText().toString(),
//                etCity.getText().toString(),
//                etBorough.getText().toString(),
//                etLat.getText().toString(),
//                etLong.getText().toString());
//        startActivity(new Intent(this, HomeActivity.class));
    }
}
