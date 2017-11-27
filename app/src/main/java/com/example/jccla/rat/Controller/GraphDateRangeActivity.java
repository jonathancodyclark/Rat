package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.jccla.rat.R;

/**
 * Created by KaihanRoman on 11/6/17.
 */


@SuppressWarnings("DefaultFileTemplate")
public class GraphDateRangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_date_range);

        CircularProgressButton bGraphDatesPicked = (CircularProgressButton) findViewById(R.id.bGraphDatesPicked);
        bGraphDatesPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGraph();
            }
        });
    }

    /**
     * Creates intent from view to be passed into Activity. gets start and end dates.
     */
    private void goToGraph() {
        Intent intent = new Intent(this, GraphActivity.class);

        DatePicker startDate = (DatePicker) findViewById(R.id.graphDatePicker);
        DatePicker endDate = (DatePicker) findViewById(R.id.graphDatePicker2);

        int startMonth = startDate.getMonth() + 1;   //adding 1 bc jan is 0 for some reason
        int startDay = startDate.getDayOfMonth();
        int startYear = startDate.getYear();
        int endMonth = endDate.getMonth() + 1;  //adding 1 bc jan is 0 for some reason
        int endDay = endDate.getDayOfMonth();
        int endYear = endDate.getYear();

        Intent retry = new Intent
                (this, com.example.jccla.rat.Controller.GraphDateRangeActivity.class);

        //check if start date is after end date. if so, restart the activity.
        if (startYear > endYear) {
            Toast.makeText(this,"Start Date cannot be after End Date.",Toast.LENGTH_LONG).show();
            startActivity(retry);
            return;
        } else if (startYear == endYear && startMonth > endMonth) {
            Toast.makeText(this,"Start Date cannot be after End Date.",Toast.LENGTH_LONG).show();
            startActivity(retry);
            return;
        } else if (startYear == endYear && startMonth == endMonth && startDay > endDay) {
            Toast.makeText(this,"Start Date cannot be after End Date.",Toast.LENGTH_LONG).show();
            startActivity(retry);
            return;
        }

        intent.putExtra("START_MONTH", startMonth);
        intent.putExtra("START_DAY", startDay);
        intent.putExtra("START_YEAR", startYear);
        intent.putExtra("END_MONTH", endMonth);
        intent.putExtra("END_DAY", endDay);
        intent.putExtra("END_YEAR", endYear);

        startActivity(intent);
    }
}
