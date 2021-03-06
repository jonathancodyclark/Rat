package com.example.jccla.rat.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by jccla on 10/23/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Rat.db";
    private static final String TABLE_NAME = "login_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "PASSWORD";
    private static final String COL_4 = "ADMIN";


    private static final String TABLE_RAT_REPORTINGS = "report_table";
    private static final String REPORT_ID = "ID";
    private static final String CREATED_DATE = "Created_Date";
    private static final String LOCATION_TYPE = "Location_Type";
    private static final String ZIPCODE = "Zipcode";
    private static final String ADDRESS = "Address";
    private static final String CITY = "City";
    private static final String BORROUGH = "Borrough";
    private static final String LATITUDE = "Latitude";
    private static final String LONGITUDE = "Longitude";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT,PASSWORD TEXT,ADMIN TEXT)");
        db.execSQL("create table " + TABLE_RAT_REPORTINGS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "CREATED_DATE TEXT,LOCATION_TYPE TEXT,ZIPCODE TEXT, ADDRESS TEXT, CITY TEXT, BORROUGH TEXT, LATITUDE TEXT, LONGITUDE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RAT_REPORTINGS);
        onCreate(db);
    }

    public boolean insertData(String username, String password, String admin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1,username.hashCode());
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,admin);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }

    //insert rat data
    public boolean insertRatData(String ID, String createdDate, String locationType, String zipcode, String address, String city, String borrough, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REPORT_ID,ID);
        contentValues.put(CREATED_DATE,createdDate);
        contentValues.put(LOCATION_TYPE,locationType);
        contentValues.put(ZIPCODE, zipcode);
        contentValues.put(ADDRESS, address);
        contentValues.put(CITY, city);
        contentValues.put(BORROUGH, borrough);
        contentValues.put(LATITUDE, latitude);
        contentValues.put(LONGITUDE, longitude);
        long result = db.insert(TABLE_RAT_REPORTINGS,null ,contentValues);
        return result != -1;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME,null);
    }

    //returns all rat data
    public Cursor getAllRatData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_RAT_REPORTINGS,null);
    }

    /**
     * This method to check user exist or not
     *
     * @param email the email the user used to sign in
     * @param password the user's password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COL_1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_2 + " = ?" + " AND " + COL_3 + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /*
          Here query function is used to fetch records from user table this function works like we use sql query.
          SQL query equivalent to this query function is
          SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;

    }
}
