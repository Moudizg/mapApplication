package com.example.mouhammad.mapapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "locations.db";
    public static final int DATABASE_VERSION = 9;
    // define constants for students table
    public static final String STUDENTS_TABLE_NAME = "location";
    public static final String STUDENTS_COLUMN_ID = "latitude";
    public static final String STUDENTS_COLUMN_NAME = "longitude";
    //define constants for courses table
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table students
        db.execSQL("create table " + STUDENTS_TABLE_NAME + " (" +
                STUDENTS_COLUMN_ID + " numeric(8), " +
                STUDENTS_COLUMN_NAME + " numeric(8) not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
        onCreate(db);
    }

    public void addLoc (LatLng latlng) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENTS_COLUMN_ID, latlng.latitude);
        contentValues.put(STUDENTS_COLUMN_NAME, latlng.longitude);
        db.insertWithOnConflict(STUDENTS_TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_ABORT);
        db.close();
    }

    public void deleteStudent (int id) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        int count = db.delete(STUDENTS_TABLE_NAME,
                STUDENTS_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
        db.close();
        if (count < 1) throw new Exception("record not found");
    }

    public ArrayList<LatLng> getAllLoc() {
        ArrayList<LatLng> array_list = new ArrayList<LatLng>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " +
                STUDENTS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new LatLng(res.getDouble(0), res.getDouble(1)));
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

}

