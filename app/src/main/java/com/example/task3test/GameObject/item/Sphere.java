package com.example.task3test.GameObject.item;

import com.example.task3test.Points.RealPoint;

public class Sphere {
    private RealPoint rp;
    private double radius;

    public Sphere(RealPoint rp, double radius) {
        this.rp = rp;
        this.radius = radius;
    }

    public RealPoint getRp() {
        return rp;
    }

    public void setRp(RealPoint rp) {
        this.rp = rp;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
