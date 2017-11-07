package com.example.jccla.rat.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.Model.SightingDataItem;
import com.example.jccla.rat.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Comparator;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static java.lang.Float.parseFloat;

/**
 * Created by KaihanRoman on 11/3/17.
 */

public class GraphActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle dates) {
        super.onCreate(dates);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        initGraph(graph);

    }
    private double sameMonthYear(SightingDataItem s) {
        String onlyDate =s.getDate().substring(0, 10);
        String[] strSplit = onlyDate.split("/");
        float month = parseFloat(strSplit[0]);
        int year = Integer.parseInt(strSplit[2]);
        return (year + (month - 1) / 12);
    }
    private void initGraph(GraphView graph) {
        int startMonth = getIntent().getIntExtra("START_MONTH", 1);
        int startDay = getIntent().getIntExtra("START_DAY", 1);
        int startYear = getIntent().getIntExtra("START_YEAR", 1);
        int endMonth = getIntent().getIntExtra("END_MONTH", 1);
        int endDay = getIntent().getIntExtra("END_DAY", 1);
        int endYear = getIntent().getIntExtra("END_YEAR", 1);

        List<SightingDataItem> sightingsInRange = Model.getInstance().getSightingsInRange(startMonth, startDay, startYear,
                endMonth, endDay, endYear);
        System.out.println(sightingsInRange);

        Map<Double, Integer> graphData = new HashMap<>();



        for (SightingDataItem s:sightingsInRange) {
            if (graphData.containsKey(sameMonthYear(s))) {
                graphData.put(sameMonthYear(s), graphData.get(sameMonthYear(s)) + 1);
            } else {
                graphData.put(sameMonthYear(s), 1);
            }
        }
        //tree map is gna sort the hashmap, prob didnt need to use hashmap at all
        Map<Double, Integer> treeMap = new TreeMap<>( new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        treeMap.putAll(graphData);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        for (Map.Entry me : treeMap.entrySet()) {
            series.appendData(new DataPoint((double) me.getKey(), (int) me.getValue()), true, 100000
            );
        }

        graph.getGridLabelRenderer().setVerticalAxisTitle("# of sightings");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        series.setDrawBackground(true);
        series.setAnimated(true);
        series.setDrawDataPoints(true);
        graph.addSeries(series);

    }


}
