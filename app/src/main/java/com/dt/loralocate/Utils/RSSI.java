package com.dt.loralocate.Utils;
import java.lang.Math;

public class RSSI {
    private double[] location;

    private double[] circle1,circle2,circle3;

    private double r1,r2,r3;

    public RSSI(double[] circle1, double[] circle2, double[] circle3){
        double t_d1 = 1;
        double t_d2 = 2;
        double t_rssi1 = 1;
        double t_rssi2 = 2;

        double A = rssi_cal_A(t_d1, t_rssi1, t_d2, t_rssi2);
        double n = rssi_cal_n(t_d1, t_rssi1, t_d2, t_rssi2);
        double d = rssi_cal_d(t_rssi1, A, n);

        this.circle1=circle1;
        this.circle2=circle2;
        this.circle3=circle3;

    }

    public void setRadius(double r1,double r2, double r3){
        this.r1=r1;
        this.r2=r2;
        this.r3=r3;
    }

    public double[] getLocation(){
        return location;
    }

    public static double rssi_cal_d(double RSSI, double A, double n) {
        return Math.pow(10, (Math.abs(RSSI) - A) / (10 * n));
    }

    public static double rssi_cal_A(double d1, double RSSI1, double d2, double RSSI2) {
        return (Math.log10(d1) * Math.abs(RSSI2) - Math.log10(d2) * Math.abs(RSSI1)) / (Math.log10(d1) - Math.log10(d2));
    }

    public static double rssi_cal_n(double d1, double RSSI1, double d2, double RSSI2) {
        return (Math.abs(RSSI1) - Math.abs(RSSI2)) / (10 * (Math.log10(d1) - Math.log10(d2)));
    }

    //Return the
    public double[] get_circle_intersection() {
        double x1 = circle1[0], y1 = circle1[1];
        double x2 = circle2[0], y2 = circle2[1];
        double x3 = circle3[0], y3 = circle3[1];

        double A = 2 * x2 - 2 * x1;
        double B = 2 * y2 - 2 * y1;
        double C = r1 * r1 - r2 * r2 - x1 * x1 + x2 * x2 - y1 * y1 + y2 * y2;
        double D = 2 * x3 - 2 * x2;
        double E = 2 * y3 - 2 * y2;
        double F = r2 * r2 - r3 * r3 - x2 * x2 + x3 * x3 - y2 * y2 + y3 * y3;

        double x = (C * E - F * B) / (E * A - B * D);
        double y = (C * D - A * F) / (B * D - A * E);

        double[] location = {x, y};
        return location;
    }
}
