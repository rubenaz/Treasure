package com.example.yaacov.treasure;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String MY_PREFS_NAME="prfes";
    SharedPreferences prefs;
    int numOfPart=0,cityId=1;
    String[] phoneNumbers;
    SQLiteDatabase db;
    AssignmentsDbHelper dbHelper;
    Vector<Place> places;
    int hintCount=0;

    //Location
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mCurrentLocation;
    String mLastUpdateTime;

    //UI
    Button openHint;
    TextView hint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        numOfPart = prefs.getInt("numOfpart",0);
        cityId = prefs.getInt("CityId" , 1);
        dbHelper = new AssignmentsDbHelper(this);
        phoneNumbers = new String[numOfPart];
        for(int i=0;i<numOfPart;i++) {
            phoneNumbers[i] = prefs.getString("number " + i, null);
        }
        places = new Vector<>();
        db = dbHelper.getReadableDatabase();
        String [] projection = {Constants.treasure.LATITUDE,Constants.treasure.LONGTITUDE,Constants.treasure.HINT,Constants.treasure.PLACE_NAME};

        Cursor c = db.query(
                Constants.treasure.TABLE_NAME, // The table to query
                projection ,               // The columns to return
                null,                // WHERE clause
                null,                // The values for the WHERE clause
                null,                //  group the rows
                null,                // filter by row groups
                null                 // The sort order
        );
        while(c.moveToNext()) {
            places.add(new Place(c.getFloat(0),c.getFloat(1),c.getString(2),c.getString(3)));
        }
        c.close();
        db.close();

        hint = (TextView)findViewById(R.id.textHint);
        openHint = (Button) findViewById(R.id.btnHint);
        openHint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hint.setText(places.get(hintCount).getHint());
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            Log.v("phoneNumber",phoneNumber);
            Log.v("MEssage",message);
            PendingIntent pi = PendingIntent.getActivity(this, 0,
                    new Intent(this, Object.class), 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);
        }catch(Exception e){
            Toast.makeText(this,e.getMessage() + "",Toast.LENGTH_LONG);
        }
    }

    //===================================Location==========================================


    //@Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
       //     LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
       //     LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            Toast.makeText(this, "No permissions", Toast.LENGTH_SHORT).show();
        }
    }

    //@Override
    public void onConnectionSuspended(int i) {

    }

   // @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(600*100);
        mLocationRequest.setSmallestDisplacement(10);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


   // @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
       // sendSMS(phone.getText().toString(), "I got more then 10m closer to you");
    }
}
