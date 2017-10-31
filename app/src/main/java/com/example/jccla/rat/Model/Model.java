package com.example.jccla.rat.Model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.R;

/**
 * Created by jccla on 10/17/2017.
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    private static List<SightingDataItem> sightings;
    private static int numAddedManuallyCount;
    private static List<SightingDataItem> sightingsAddedManuallyList;

    public void putInLinkedList(int key, String dt, String lt, String zip, String addr, String city, String borough, String lat, String lng) {
        SightingDataItem aSighting = new SightingDataItem(numAddedManuallyCount, key, dt, lt, zip, addr, city, borough, lat, lng);
        sightingsAddedManuallyList.add(aSighting);
        numAddedManuallyCount++;
    }

    public List<SightingDataItem> getSightingsAddedManuallyList() {
        return sightingsAddedManuallyList;
    }

    public int getNumAddedManuallyCount(){
        return numAddedManuallyCount;
    }

    public Model() {
        sightings = new LinkedList<>();
        sightingsAddedManuallyList = new LinkedList<SightingDataItem>();
        numAddedManuallyCount = 0;
    }

    public static Model getInstance() {
        return _instance;
    }

    public void addSighting(SightingDataItem sighting) {
        sightings.add(sighting);
    }

    public void setSightings() {
        sightings = new LinkedList<>();
    }

    public List<SightingDataItem> getItems() {
        return sightings;
    }

    public SightingDataItem getItemById(int i) {
        return sightings.get(i);
    }

    public void readCSV(InputStream is) {
        int count = 0;
        setSightings(); //make the sightings = null before loading in from manually added and CSV file
        List<SightingDataItem> list = getSightingsAddedManuallyList();
        for (SightingDataItem sda: list) {
            addSighting(sda);
            count++;
        }
        try {
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
                        addSighting(new SightingDataItem(count, Integer.parseInt(tokens[0]), tokens[1], tokens[7], tokens[8], tokens[9],
                                tokens[16], tokens[23], tokens[49], tokens[50]));
                    }

                } catch (Exception e) {
                    Log.wtf("PostLoginActivity", "Error reading data on line" + line, e);
                }
                count++;
            }

            br.close();
        } catch (Exception e) {
            Log.e("Error in Model:", "error reading CSV", e);
        }

    }

    public List<SightingDataItem> getSightingsInRange(int sm, int sd, int sy, int em, int ed, int ey) {
        List<SightingDataItem> filteredList = new ArrayList<SightingDataItem>();
        for (SightingDataItem s : sightings) {
            if (isDateInRange(s.getDate(), sm, sd, sy, em, ed, ey)) {
                filteredList.add(s);
                Log.d("FilteredList added ", "" + s.getKey());
            }
        }
        return filteredList;
    }

    private boolean isDateInRange(String date, int sm, int sd, int sy, int em, int ed, int ey) {

        //given date format example: 9/14/2015  12:00:00 AM
        String onlyDate = date.substring(0, 10);     //cut off time part
        String[] dateInts = onlyDate.split("/");
        int month = Integer.parseInt(dateInts[0]);
        int day = Integer.parseInt(dateInts[1]);
        int year = Integer.parseInt(dateInts[2]);

        if (year < sy || year > ey) {
            return false;
        } else if (year == sy && month < sm) {
            return false;
        } else if (year == sy && month == sm && day < sd) {
            return false;
        } else if (year == ey && month > em) {
            return false;
        } else if (year == ey && month == em && day > ed) {
            return false;
        } else {
            return true;
        }
    }

}
