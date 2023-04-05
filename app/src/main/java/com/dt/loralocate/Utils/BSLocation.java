package com.dt.loralocate.Utils;

public class BSLocation {
    //The Lat&Lng of three test LoRa base station
    final static private double[] location1={39.092709,121.823777};
    final static private double[] location2={39.092663,121.824752};
    final static private double[] location3={39.092002,121.824325};

    public static double[] getLocation1() {
        return location1;
    }

    public static double[] getLocation2() {
        return location2;
    }

    public static double[] getLocation3() {
        return location3;
    }
}
