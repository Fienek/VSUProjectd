package com.example.task3test.Points;

import android.util.Log;

public class RealPoint {
    private double x, y;

    public RealPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public RealPoint minus(RealPoint p){
        return new RealPoint( getX() - p.getX(), getY() - p.getY());
    }
    public RealPoint plus(RealPoint p){
        return new RealPoint( p.getX() + getX(), p.getY() + getY());
    }
    public RealPoint sub(RealPoint p){
        return new RealPoint(getX()* p.getX(), getY()*p.getY());
    }
    public RealPoint sub(double a){
        return new RealPoint(getX()* a, getY()*a);
    }
    public RealPoint div(double a){
        return new RealPoint(getX()/ a, getY()/a);
    }
    public static double findCorner(RealPoint v1, RealPoint v2){
        double a = v1.getX()*v2.getX() + v1.getY()*v2.getY();
        double b = Math.sqrt(v1.getX()* v1.getX() + v1.getY()*v1.getY());
        double c = Math.sqrt(v2.getX()* v2.getX() + v2.getY()*v2.getY());
        double cos = a/(b*c);
        double resutl = Math.toDegrees(Math.acos(cos));
        return resutl;

    }

    public static double findDistVector(RealPoint rp){
        double x = Math.abs(rp.getX());
        double y = Math.abs(rp.getY());
        return Math.sqrt(x*x+y*y);
    }
    public RealPoint findVectro(RealPoint end){
        return end.minus(this);
    }

    @Override
    public String toString() {
        return "RealPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
