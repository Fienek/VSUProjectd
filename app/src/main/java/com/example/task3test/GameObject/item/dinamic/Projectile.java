package com.example.task3test.GameObject.item.dinamic;

import android.util.Log;

import com.example.task3test.Points.RealPoint;

import java.util.List;

public class Projectile  {
    private RealPoint rp;
    private double radius;
    private double speed;
    private double ax = 0;
    private boolean moveObject = true;

    public RealPoint getRp() {
        return rp;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setRp(RealPoint rp) {
        this.rp = rp;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isMoveObject() {
        return moveObject;
    }

    public Projectile(RealPoint rp, double radius, double speed) {
        this.speed = speed;
        this.rp = rp;
        this.radius = radius;
        Log.i("qqqqqqq1", "dada " + rp.toString());
    }
    
    long csTime1 = System.currentTimeMillis();
    public void updateWheel(List<Double> earthPoints){
        long csTime2 = System.currentTimeMillis();
        double dt =  (csTime2-csTime1) * 0.01;
        csTime1 = csTime2;


        double xnew =  rp.getX() +  dt * speed + ax * dt*dt/2;
        if(xnew+1 >= earthPoints.size()){
            stop();
            return;
        } if(xnew-1 <0){
            stop();
            return;
        }
        double test = xnew - (int)(xnew);
        double ynew = earthPoints.get((int) xnew) + (earthPoints.get((int) (xnew+1)) - earthPoints.get((int) xnew))*test;


        rp = new RealPoint(xnew, ynew+radius);
    }




    private void stop(){
        moveObject = false;
    }


















}
