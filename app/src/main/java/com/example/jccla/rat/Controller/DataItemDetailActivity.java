package com.example.jccla.rat.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.Model.SightingDataItem;
import com.example.jccla.rat.R;

public class DataItemDetailActivity extends AppCompatActivity {
    private SightingDataItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_item_detail);

        int index = getIntent().getIntExtra("index", 1); //1 is a default return value
        mItem = Model.getInstance().getItemById(index);

        ((TextView) findViewById(R.id.detail_key)).setText("" + mItem.getKey());
        ((TextView) findViewById(R.id.date)).setText(mItem.getDate());
        ((TextView) findViewById(R.id.location_type)).setText(mItem.getLocationType());
        ((TextView) findViewById(R.id.zip)).setText(mItem.getZip());
        ((TextView) findViewById(R.id.detail_address)).setText(mItem.getAddress());
        ((TextView) findViewById(R.id.city)).setText(mItem.getCity());
        ((TextView) findViewById(R.id.borough)).setText(mItem.getBorough());
        ((TextView) findViewById(R.id.latitude)).setText(mItem.getLatitude());
        ((TextView) findViewById(R.id.longitude)).setText(mItem.getLongitude());

    }
}