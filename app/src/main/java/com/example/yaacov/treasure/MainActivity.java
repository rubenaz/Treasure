package com.example.yaacov.treasure;

import android.Manifest;
import android.app.PendingIntent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
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
    Location hintLocation;
    String mLastUpdateTime;
    LocationListener locationListener;

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
        extractPlacesFromDB();

        hint = findViewById(R.id.textHint);
        openHint = findViewById(R.id.btnHint);
        openHint.setOnClickListener(this);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mCurrentLocation = location;
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                Log.v("location",location.toString());
                Log.v("hint place",places.elementAt(hintCount).toString());
                //TODO equal currentLocation to hint location,sen sms to other groups,then open new hint, if end say congrats
                float distance;
                if(hintLocation != null) {
                    distance = hintLocation.distanceTo(mCurrentLocation);
                    Log.v("distance", distance + "");
                    if(distance<100){//got to the current place
                        sendSmsToAllParticipants();
                        hintCount++;
                        if(hintCount==places.size()){
                            goToFinish();
                        }
                        else {
                            hint.setText("פתח את הרמז הבא");
                            openHint.setVisibility(View.VISIBLE);
                        }
                    }
                }


            }
        };
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        this.createLocationRequest();
    }

    private void extractPlacesFromDB(){
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
    }
    @Override
    public void onClick(View v) {//click on open hint
        hint.setText(places.get(hintCount).getHint());
        hintLocation = createNewLocation(places.elementAt(hintCount).getLongt(),places.elementAt(hintCount).getLat());
        openHint.setVisibility(View.INVISIBLE);
    }

    private void goToFinish(){
        Intent i = new Intent(this, finishActivity.class);
        startActivity(i);
    }
    private String countIntToString(int hintCount){
        if(hintCount==1){
            return "1st";
        }
        else if(hintCount==2){
            return "2nd";
        }
        else if(hintCount==3){
            return "3rd";
        }
        else
            return hintCount + "th";
    }

    private void sendSmsToAllParticipants(){
        for(String number : phoneNumbers){
            if(hintCount==places.size()-1)
                sendSMS(number,"Our team got to last location!");
            else
                sendSMS(number,"Our team got to " + countIntToString(hintCount+1) + " hint");
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            PendingIntent pi = PendingIntent.getActivity(this, 0,
                    new Intent(this, Object.class), 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);
        }catch(Exception e){
            Toast.makeText(this,e.getMessage() + "",Toast.LENGTH_LONG);
        }
    }

    //===================================Location==========================================


    Location createNewLocation(double longitude, double latitude) {
        Location location = new Location("dummyprovider");
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return location;
    }
    //@Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
       //     LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, locationListener);
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
        mLocationRequest.setInterval(60*100);
        mLocationRequest.setSmallestDisplacement(10);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


   // @Override
    /*public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
       // sendSMS(phone.getText().toString(), "I got more then 10m closer to you");
    }*/

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
