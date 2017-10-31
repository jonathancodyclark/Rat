package com.example.jccla.rat.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
* Created by KaihanRoman on 10/31/17.
*/



public class RepDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sightings.db";
    public static final String TABLE_NAME = "sightings_table";
    public static final String COL_1 = "DateAndTime";
    public static final String COL_2 = "LocationType";
    public static final String COL_3 = "ZipCode";
    public static final String COL_4 = "Address";
    public static final String COL_5 = "City";
    public static final String COL_6 = "Borough";
    public static final String COL_7 = "Lat";
    public static final String COL_8 = "Long";




    public RepDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT,PASSWORD TEXT,ADMIN TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    /*public boolean insertData(String dateAndTime, String locationType, String zipCode, String address,
    city, String borough, String lat, String long) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,dateAndTime);
        contentValues.put(COL_2,locationType);
        contentValues.put(COL_3,zipCode);
        contentValues.put(COL_4,address);
        contentValues.put(COL_5,city);
        contentValues.put(COL_6,borough);
        contentValues.put(COL_7,lat);
        contentValues.put(COL_8,long);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    */


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }


}


