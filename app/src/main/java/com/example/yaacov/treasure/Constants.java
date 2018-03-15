package com.example.yaacov.treasure;

import android.provider.BaseColumns;


public final class Constants {
    private Constants(){
        throw new AssertionError("Can't create constants class");
    }
    public static abstract class treasure implements BaseColumns {
        public static final String TABLE_NAME = "hints";
        public static final String CITY_ID = "city_id";
        public static final String LATITUDE = "latitude";
        public static final String LONGTITUDE = "longtitude";
        public static final String HINT = "hint";
        public static final String PLACE_NAME = "place_name";
    }
}