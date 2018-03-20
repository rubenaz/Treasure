package com.example.yaacov.treasure;

public class Place {

    private String hint;
    private String name;
    private float lat,longt;

    public Place(float lat,float longt,String hint,String name){
        this.lat = lat;
        this.longt = longt;
        this.hint = hint;
        this.name = name;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLongt() {
        return longt;
    }

    public void setLongt(float longt) {
        this.longt = longt;
    }

    public String toString(){
        return "Lat:" + lat +"\n" +
                "Long:" + longt + "\n" +
                "hint:" + hint + "\n" +
                "name:" + name;
    }


}
