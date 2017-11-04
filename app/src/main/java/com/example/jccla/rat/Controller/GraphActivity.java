package com.example.jccla.rat.Controller;

/**
 * Created by KaihanRoman on 11/3/17.
 */

public class GraphActivity {
    GraphView graph = (GraphView) findViewById(R.id.graph);
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3)
    });
graph.addSeries(series);
}
