package com.example.task3test.GameObject.item.dinamic;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.task3test.DrawClass;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;

import java.util.List;

public class Snaryad {
    private double v;
    private double radius;
    private double x;
    private static final double G = 9.8;
    private double angel;
    private RealPoint rpJ;
    private RealPoint rp;
    private RealPoint rpStar;
    private RealPoint rpStart;
    private RealPoint rpVersh;
    private boolean isStop = false;
    private boolean isCreate = false;
    private double anglePol;
    private double b = 0;
    private boolean da = false;

    public Snaryad(double v, double angel, RealPoint rpStart, double radius) {
        this.v = v;
        this.angel = angel;
        this.rp = this.rpStart = this.rpStar = rpStart;
        this.radius = radius;
        this.rpVersh = new RealPoint(rp.getX()+findS()/2, rp.getY()+findH());
        this.rpJ = new RealPoint(0,0);
        Log.i("qqqqq2", rpVersh.toString());
    }



    public void setRp(RealPoint rp) {
        this.rp = rp;
    }

    long csTime1 = System.currentTimeMillis();
    public void update(){
        if(!isStop && isCreate){
            rpStar = rp;
            double angelR = Math.toRadians(angel);
            long csTime2 = System.currentTimeMillis();
            double dt =  (csTime2-csTime1) * 0.001;
            csTime1 = csTime2;
            double x = rpJ.getX() +  dt * v + 0 * dt*dt/2;
            double y = x * Math.tan(angelR) - ((G*x*x)/(2*(v*v)*Math.cos(angelR)*Math.cos(angelR)));
            //double y = x*Math.tan(angelR) - ((G*x*x)/(2*v*v)) * (1+Math.tan(angelR)*Math.tan(angelR));

            RealPoint rpNew = new RealPoint(x,y).plus(rpStart);
            rpJ = new RealPoint(x, y);
            setRp(rpNew);
            RealPoint da = rpStar.findVectro(rp);
            if(rpStar.getY() < rp.getY()){
                anglePol = -RealPoint.findCorner(da, new RealPoint(1,0));
            }else{
                anglePol = RealPoint.findCorner(da, new RealPoint(1,0));
            }

        }
    }

    public void draw(Canvas canvas, ScreenConverter sc, Drawable d1, Drawable d2){
        if(da){
            DrawClass.drawSprite(canvas, sc, d1, rp, radius*2, 89/26, anglePol);
        }else {
            DrawClass.drawSprite(canvas, sc, d2, rp, radius*2, 89/26, anglePol);
        }
        if(!isStop){
            b++;
        }

        if (b >= 5) {
            if(da){
                da = false;
            } else
                da = true;
            b = 0;
        }
        //DrawClass.drawSprite(canvas, sc, d1, rp, radius*2, 89/26, anglePol);
    }

    public boolean checkEarth(List<Double> earth){
        if(!isStop)
            return rp.getY() - radius < earth.get((int) rp.getX());
        return false;
    }
    public boolean checkTank(HitSphere hitSphere){
        if(!isStop)
            return hitSphere.findMinSize(this);
        return false;
    }
    public void stop(){
        isStop = true;
    }

    private double findS(){
        double angelRad = Math.toRadians(angel);
        double sin = Math.sin(2*angelRad);
        return ((v * v)/G)*sin;
    }
    private double findH(){
        double angelRad = Math.toRadians(angel);
        double sin = Math.sin(angelRad);
        return ((v * v)/(2*G)) * sin*sin;
    }

    public double getRadius() {
        return radius;
    }

    public RealPoint getRp() {
        return rp;
    }
    public boolean isStop() {
        return isStop;
    }

    public boolean isCreate() {
        return isCreate;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }

    public void setAngel(double angel) {
        this.angel = angel;
    }

    public RealPoint getRpStart() {
        return rpStart;
    }
}
