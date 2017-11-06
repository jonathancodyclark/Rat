package com.example.jccla.rat.Controller;

import android.support.v7.app.AppCompatActivity;

import com.example.jccla.rat.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by KaihanRoman on 11/3/17.
 */

public class GraphActivity extends AppCompatActivity {

    @Override
    public void onCreate(FullscreenActivity activity) {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph);
        initGraph(graph);
    }
    @Override
    public void initGraph(GraphView graph) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)});
        series.setDrawBackground(true);
        series.setAnimated(true);
        series.setDrawDataPoints(true);
        graph.addSeries(series);

    }
    GraphView graph = (GraphView) findViewById(R.id.graph);

    graph.addSeries(series);
}
