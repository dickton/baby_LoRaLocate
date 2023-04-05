package com.dt.loralocate.Utils;

import android.util.Log;

public class CoordinateConversion {
    private double originLong;
    private double originLat;

    private final static double LONGITUDE_LENGTH=40030173;
    private final static double LATITUDE_LENGTH=40030173;

    //Set the origin point's Lng&Lat
    public CoordinateConversion(double originLat,double originLong){
        this.originLong=originLong;
        this.originLat=originLat;
    }

    //Convert Rectangular Plane Coordinate System into Lng&Lat
    public double[] MeterToLL(double x, double y){
        y=this.originLat+y*360/LONGITUDE_LENGTH;
        x=this.originLong+x*360/LATITUDE_LENGTH;
        //return {[latitude], [longitude]}
        return new double[]{y, x};
    }

    //Convert Lng&Lat into Rectangular Plane Coordinate System
    //ll[0] is Latitude, ll[1] is Longitude
    public double[] LLtoMeter(double[] ll){
        double x,y;
        y=(ll[0]-this.originLat)*LONGITUDE_LENGTH/360;
        x=(ll[1]-this.originLong)*LATITUDE_LENGTH/360;
        return new double[]{x,y};
    }
}
