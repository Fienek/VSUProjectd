package com.example.task3test;

import android.util.Log;

import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class SphereBtn {
    private ScreenPoint sp1;
    private double radius;


    public SphereBtn(ScreenPoint sp1, double radius) {
        this.sp1 = sp1;
        this.radius = radius;
    }

    public ScreenPoint getSp1() {
        return sp1;
    }

    public void setSp1(ScreenPoint sp1) {
        this.sp1 = sp1;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public boolean clickOnBtn(double x, double y, ScreenConverter sc){
        RealPoint rp1 = sc.s2r(new ScreenPoint((int) x, (int) y));
        RealPoint rp2 = sc.s2r(sp1);
        RealPoint v = rp1.findVectro(rp2);
        double size = RealPoint.findDistVector(v);
        Log.i("qqqqqqqq6", "size = " + size + " v = " + v.toString() + " r = " + radius );
        if(size <= radius) {
            return true;
        }
        return false;
    }
}
