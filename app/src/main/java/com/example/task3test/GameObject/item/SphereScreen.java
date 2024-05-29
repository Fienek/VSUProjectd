package com.example.task3test.GameObject.item;

import com.example.task3test.Points.ScreenPoint;

public class SphereScreen {
    ScreenPoint sp;
    double radius;

    public SphereScreen(ScreenPoint sp, double radius) {
        this.sp = sp;
        this.radius = radius;
    }

    public ScreenPoint getSp() {
        return sp;
    }

    public void setSp(ScreenPoint sp) {
        this.sp = sp;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
