package com.example.yaacov.treasure;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;




public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    final private int MY_PERMISSIONS_REQUEST_LOCATION=1;
    final private int MY_PERMISSIONS_REQUEST_SMS=2;
    private Button btnSign;
    private AssignmentsDbHelper dbHelper;
    private SQLiteDatabase db;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnSign = findViewById(R.id.btnSign);
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
        dbHelper.deleteDBContent(db);
        dbHelper.onCreate(db);
        ContentValues values = new ContentValues();

        //Jerusalem
        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,31.772134485977737);
        values.put(Constants.treasure.LONGTITUDE,35.20413413643837);
        values.put(Constants.treasure.HINT," המוזיאון הגדול והחשוב בישראל ומייסדו הוא טדי קולק");
        values.put(Constants.treasure.PLACE_NAME,"מוזיאון ישראל");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,31.776863659535742);
        values.put(Constants.treasure.LONGTITUDE,35.20562678575516);
        values.put(Constants.treasure.HINT,"בית המחוקקים והנבחרים של מדינת ישראל");
        values.put(Constants.treasure.PLACE_NAME,"הכנסת");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,"31.769021855543386");
        values.put(Constants.treasure.LONGTITUDE,"35.21562337875366");
        values.put(Constants.treasure.HINT,"מתחם התרבות והאמנות הגדול בישראל");
        values.put(Constants.treasure.PLACE_NAME,"תיאטרון ירושלים");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,1);
        values.put(Constants.treasure.LATITUDE,"31.774966526080846");
        values.put(Constants.treasure.LONGTITUDE,"35.2175747264705");
        values.put(Constants.treasure.HINT,"לכיכר היו שמות רבים במהלך ההיסטוריה ובהם: \"כיכר הרפובליקה\", \"כיכר המלכים\" ו\"כיכר הגר\"");
        values.put(Constants.treasure.PLACE_NAME,"כיכר צרפת");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        //Tel-Aviv
        values.put(Constants.treasure.CITY_ID,2);
        values.put(Constants.treasure.LATITUDE,32.074547720261826);
        values.put(Constants.treasure.LONGTITUDE,34.79132294391252);
        values.put(Constants.treasure.HINT,"קומפלקס של שלושה גורדי שחקים שהגבוה מביניהם הוא בן 49 קומות");
        values.put(Constants.treasure.PLACE_NAME,"עזריאלי");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,2);
        values.put(Constants.treasure.LATITUDE,32.0800567996409);
        values.put(Constants.treasure.LONGTITUDE,34.78087415934101);
        values.put(Constants.treasure.HINT,"הכיכר העירונית הראשית של תל אביב,הכיכר נקראה בעבר כיכר מלכי ישראל");
        values.put(Constants.treasure.PLACE_NAME,"כיכר רבין");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,2);
        values.put(Constants.treasure.LATITUDE,"32.05180377601124");
        values.put(Constants.treasure.LONGTITUDE,"34.761722127478606");
        values.put(Constants.treasure.HINT,"הקבוצה שזה מגרשה הביתי משחקת בימי שישי שעה 15:00");
        values.put(Constants.treasure.PLACE_NAME,"בלומפילד");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }


        values.put(Constants.treasure.CITY_ID,2);
        values.put(Constants.treasure.LATITUDE,"32.07231720800567");
        values.put(Constants.treasure.LONGTITUDE,"34.77979307419673");
        values.put(Constants.treasure.HINT,"התיאטרון נוסד תחילה בשנת 1914 על ידי נחום דוד צמח.");
        values.put(Constants.treasure.PLACE_NAME,"הבימה");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }


        //Paris
        values.put(Constants.treasure.CITY_ID,3);
        values.put(Constants.treasure.LATITUDE,48.80379953759415);
        values.put(Constants.treasure.LONGTITUDE, 2.1202250543683476);
        values.put(Constants.treasure.HINT,"ארמונם העיקרי של מלכי צרפת בשלהי המאה ה-17 ובמאה ה-18");
        values.put(Constants.treasure.PLACE_NAME,"ארמון ורסאי");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,3);
        values.put(Constants.treasure.LATITUDE,48.8583905296204);
        values.put(Constants.treasure.LONGTITUDE,2.2944259643554688);
        values.put(Constants.treasure.HINT,"נבנה בשנת 1889,והוא אחד האתרים המפורסמים ביותר בעולם,הגובה שלו היא 324 מטר");
        values.put(Constants.treasure.PLACE_NAME,"מגדל אייפל");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,3);
        values.put(Constants.treasure.LATITUDE,"48.87386089807715");
        values.put(Constants.treasure.LONGTITUDE,"2.294940948486328");
        values.put(Constants.treasure.HINT,"נבנה בפקודתו של נפוליאון,גובהו כ-50 מטר, ורוחבו כ-45 מטר. על חלקו העליון של עיטור תיאור יציאת המתנדבים ב-1792");
        values.put(Constants.treasure.PLACE_NAME,"שער הניצחון");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }


        values.put(Constants.treasure.CITY_ID,3);
        values.put(Constants.treasure.LATITUDE,"48.8606139835832 ");
        values.put(Constants.treasure.LONGTITUDE,"2.337641716003418");
        values.put(Constants.treasure.HINT,"הוא אחד המוזיאונים הוותיקים, הגדולים והמפורסמים ביותר בעולם. ההיסטוריה של המבנה החלה כמעט לפני אלף שנה, והמבנה הנוכחי התפתח מארמון שהחל להיבנות במאה השש-עשרה");
        values.put(Constants.treasure.PLACE_NAME,"מוזאון הלובר");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        //Barcelona
        values.put(Constants.treasure.CITY_ID,4);
        values.put(Constants.treasure.LATITUDE,41.387016039395476);
        values.put(Constants.treasure.LONGTITUDE,  2.17004656791687);
        values.put(Constants.treasure.HINT,"היא כיכר גדולה במרכז ברצלונה. הכיכר נחשבת גם למרכז העיר וגם למקום בו העיר העתיקה");
        values.put(Constants.treasure.PLACE_NAME,"כיכר קטלוניה");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,4);
        values.put(Constants.treasure.LATITUDE,41.39107282369714);
        values.put(Constants.treasure.LONGTITUDE,2.180614471435547);
        values.put(Constants.treasure.HINT,"המבנה נבנה לכבוד התערוכה העולמית של ברצלונה (1888), כשער כניסה המרכזי אליה על ידי האדריכל ג'וזף וילסקה אי קאסנובס");
        values.put(Constants.treasure.PLACE_NAME,"שער הניצחון");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }

        values.put(Constants.treasure.CITY_ID,4);
        values.put(Constants.treasure.LATITUDE,"41.37687297118964");
        values.put(Constants.treasure.LONGTITUDE,"2.184305191040039");
        values.put(Constants.treasure.HINT,"האתר גדול ומכיל אלפי סוגים של דגים וכמה מביניהם הם כרישים");
        values.put(Constants.treasure.PLACE_NAME,"האקווריום");
        try {
            id = db.insert(Constants.treasure.TABLE_NAME, null, values);
        }catch(android.database.sqlite.SQLiteConstraintException e){
            Log.v("SqlException:",e.toString());
        }


        values.put(Constants.treasure.CITY_ID,4);
        values.put(Constants.treasure.LATITUDE,"41.380898187591235\n ");
        values.put(Constants.treasure.LONGTITUDE,"2.1228182315826416");
        values.put(Constants.treasure.HINT,"קאמפ נאו");
        values.put(Constants.treasure.PLACE_NAME,"אצטדיון כדורגל הנמצא בעיר ברצלונה ");
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
