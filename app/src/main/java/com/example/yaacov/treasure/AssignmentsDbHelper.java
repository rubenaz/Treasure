package com.example.yaacov.treasure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class AssignmentsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "treasure.db";

    public AssignmentsDbHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Constants.treasure.TABLE_NAME + " (" +
                    Constants.treasure._ID + " INTEGER PRIMARY KEY," +
                    Constants.treasure.CITY_ID + " INTEGER NOT NULL," +
                    Constants.treasure.LATITUDE + " INTEGER NOT NULL," +
                    Constants.treasure.LONGTITUDE + " INTEGER NOT NULL," +
                    Constants.treasure.HINT + " TEXT NOT NULL," +
                    Constants.treasure.PLACE_NAME + " TEXT NOT NULL" +
                    ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Constants.treasure.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}