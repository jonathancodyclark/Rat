package com.example.jccla.rat.Model;

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

}
