package com.example.jccla.rat.Model;

/**
 * Created by jccla on 10/17/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class SightingDataItem {

    private final int index;
    private final int key;
    private final String date;
    private final String locationType;
    private final String zip;
    private final String address;
    private final String city;
    private final String borough;
    private final String latitude;
    private final String longitude;

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

    /**
     * @return the key of the sighting
     */
    public int getKey() {
        return key;
    }

    /**
     * @return the address of the sighting
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the index in the list of the sighting
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the date of the sighting
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the location type of the sighting
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * @return the zip code of the sighting
     */
    public String getZip() {
        return zip;
    }

    /**
     * @return the city of the sighting
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the borough of the sighting
     */
    public String getBorough() {
        return borough;
    }

    /**
     * @return the latitude of the sighting
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude of the sighting
     */
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return key + ": " + address;
    }
}
