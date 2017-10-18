package com.example.jccla.rat.Model;

/**
 * Created by jccla on 10/17/2017.
 */

public class SightingDataItem {

    private int index;
    private int key;
    private String date;
    private String locationType;
    private String zip;
    private String address;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

    public SightingDataItem (int i, int k, String d, String lt, String z, String a, String c, String b, String lat, String lon) {
        index = i;
        key = k;
        date = d;
        locationType = lt;
        zip = z;
        address = a;
        city = c;
        borough = b;
        latitude = lat;
        longitude = lon;
    }

    public int getKey() {
        return key;
    }

    public String getAddress() {
        return address;
    }

    public int getIndex() {
        return index;
    }

    public String getDate() {
        return date;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getBorough() {
        return borough;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return key + ": " + address;
    }
}
