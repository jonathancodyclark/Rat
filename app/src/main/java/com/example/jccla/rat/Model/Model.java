package com.example.jccla.rat.Model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jccla on 10/17/2017.
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    private static List<SightingDataItem> sightings;
    private static int numAddedManuallyCount;
    private static List<SightingDataItem> sightingsAddedManuallyList;

    /**
     * Creates a new sighting from the params and adds it to the LinkedList
     * @param key
     * @param dt
     * @param lt
     * @param zip
     * @param addr
     * @param city
     * @param borough
     * @param lat
     * @param lng
     */
    public void putInLinkedList(int key, String dt, String lt, String zip, String addr, String city, String borough, String lat, String lng) {
        SightingDataItem aSighting = new SightingDataItem(numAddedManuallyCount, key, dt, lt, zip, addr, city, borough, lat, lng);
        sightingsAddedManuallyList.add(aSighting);
        numAddedManuallyCount++;
    }

    /**
     *
     * @return the list of the sightings that were manually added by the user
     */
    public List<SightingDataItem> getSightingsAddedManuallyList() {
        return sightingsAddedManuallyList;
    }

    /**
     *
     * @return the number of manually added sightings
     */
    public int getNumAddedManuallyCount(){
        return numAddedManuallyCount;
    }

    public Model() {
        sightings = new LinkedList<>();
        sightingsAddedManuallyList = new LinkedList<SightingDataItem>();
        numAddedManuallyCount = 0;
    }

    /**
     *
     * @return the singleton instance of the Model
     */
    public static Model getInstance() {
        return _instance;
    }

    /**
     * Adds a sighting to the list in the Model.
     * @param sighting
     */
    public void addSighting(SightingDataItem sighting) {
        sightings.add(sighting);
    }

    /**
     * sets sightings to an empty LinkedList
     */
    public void setSightings() {
        sightings = new LinkedList<>();
    }

    /**
     *
     * @return the sightings list from the model
     */
    public List<SightingDataItem> getItems() {
        return sightings;
    }

    /**
     *
     * @param i the id of the sighting (index in the list)
     * @return the sighting at that id
     */
    public SightingDataItem getItemById(int i) {
        return sightings.get(i);
    }


    /**
     * reads sightings from the CSV into the list in the Model.
     *
     * @param is the InputStream from the LoginActivity
     */
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

    /**
     * Method to filter the list of sightings to return the ones within the given date range
     *
     * @param sm the start month
     * @param sd the start day
     * @param sy the start year
     * @param em the end month
     * @param ed the end day
     * @param ey the end year
     * @return the filtered list of all sightings in the given date range.
     */
    public List<SightingDataItem> getSightingsInRange(int sm, int sd, int sy, int em, int ed, int ey) {
        List<SightingDataItem> filteredList = new ArrayList<SightingDataItem>();
        for (SightingDataItem s : sightings) {
            if (isDateInRange(s.getDate(), sm, sd, sy, em, ed, ey)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    /**
     * Method to determine if a date is within a given range.
     *
     * @param date the date of the sighting
     * @param sm the start month
     * @param sd the start day
     * @param sy the start year
     * @param em the end month
     * @param ed the end day
     * @param ey the end year
     * @return true if given date string is in range, false otherwise
     */
    public boolean isDateInRange(String date, int sm, int sd, int sy, int em, int ed, int ey) {

        //given date format example: 9/14/2015  12:00:00 AM
        String onlyDate = date.substring(0, 10);     //cut off time part
        String[] dateInts = onlyDate.split("/");
        int month = Integer.parseInt(dateInts[0]);
        int day = Integer.parseInt(dateInts[1]);
        int year = Integer.parseInt(dateInts[2].split(" ")[0]);

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
