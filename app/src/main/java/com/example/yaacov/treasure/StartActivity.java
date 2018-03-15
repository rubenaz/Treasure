package com.example.yaacov.treasure;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    final int MY_PERMISSIONS_REQUEST_LOCATION=1;
    final int MY_PERMISSIONS_REQUEST_SMS=2;
    Button btnSign;
    AssignmentsDbHelper dbHelper;
    SQLiteDatabase db;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnSign = (Button) findViewById(R.id.btnSign);
        btnSign.setOnClickListener(this);

        dbHelper = new AssignmentsDbHelper(this);

        addHints();
        ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_LOCATION);

    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION:
            }
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.w("MainActivity", "Permissions was granteed");
                } else {
                    Log.e("MainActivity", "Permissions was denied");
                }
            }
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_SMS:
            }
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.w("MainActivity", "Permissions was granteed");
                } else {
                    Log.e("MainActivity", "Permissions was denied");
                }
            }
        }
    }

    public void addHints(){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,31.772134485977737);
        values.put(Constants.treasure.LONGTITUDE,35.20413413643837);
        values.put(Constants.treasure.HINT,"מוזיאון ישראל");
        values.put(Constants.treasure.PLACE_NAME,"מוזיאון ישראל");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,31.776863659535742);
        values.put(Constants.treasure.LONGTITUDE,35.20562678575516);
        values.put(Constants.treasure.HINT,"הכנסת");
        values.put(Constants.treasure.PLACE_NAME,"הכנסת");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,"");
        values.put(Constants.treasure.LONGTITUDE,"");
        values.put(Constants.treasure.HINT,"");
        values.put(Constants.treasure.PLACE_NAME,"");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,"");
        values.put(Constants.treasure.LONGTITUDE,"");
        values.put(Constants.treasure.HINT,"");
        values.put(Constants.treasure.PLACE_NAME,"");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,"");
        values.put(Constants.treasure.LONGTITUDE,"");
        values.put(Constants.treasure.HINT,"");
        values.put(Constants.treasure.PLACE_NAME,"");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }
        db.close();

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, phonesActivity.class);
        startActivity(i);
    }
}
