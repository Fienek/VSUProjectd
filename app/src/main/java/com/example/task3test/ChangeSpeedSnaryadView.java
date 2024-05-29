package com.example.task3test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.example.task3test.GameObject.item.SphereScreen;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class ChangeSpeedSnaryadView {

    private SphereScreen sphere;

    private ScreenPoint sp1, sp2;
    private ScreenConverter sc;
    private double size;
    private double oneProzent;
    private RealPoint rpCenter;
    private RealPoint vector;
    private double height;
    private double edinPlusSpeed = 2.5;
    private double startSpeed =24;
    private ScreenPoint spCenter;

    public ChangeSpeedSnaryadView(ScreenPoint sp1, ScreenPoint sp2, double height, ScreenConverter sc) {
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.sphere = new SphereScreen(sp1, height/2);
        this.sc = sc;
        RealPoint p1 = sc.s2r(sp1);
        RealPoint p2 = sc.s2r(sp2);
        this.vector = p1.findVectro(p2);
        this.size = RealPoint.findDistVector(vector);
        this.oneProzent = size/100;
        this.rpCenter = vector.div(2).plus(sc.s2r(sp1));
        this.height = height;
        this.spCenter = sc.r2s(rpCenter);
    }

    public boolean isClick(double x, double y){
        RealPoint rp = sc.s2r(new ScreenPoint((int) x, (int) y));
        if(rp.getX() >= sc.sDist2rDist(sp1.getX()) && rp.getX() <= sc.sDist2rDist(sp2.getX())
        && rp.getY() <= rpCenter.getY()+height/2  && rp.getY() >= rpCenter.getY() - height/2){
            return true;
        }
        return false;
    }



    Paint p = new Paint();
    {
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void draw(Canvas canvas, Drawable d, Drawable d2){
        p.setColor(Color.BLACK);
        ScreenPoint p1 = new ScreenPoint((sp1.getX()-sc.rDist2sDist(height/2)), sp1.getY()+sc.rDist2sDist(height/2));
        ScreenPoint p2 = new ScreenPoint((sp2.getX() + sc.rDist2sDist(height/2)), sp2.getY()-sc.rDist2sDist(height/2));


        //DrawClass.drawRect(canvas, p1, p2, p);
        DrawClass.drawSprite(canvas, sc, d, sc.s2r(spCenter),size, 320/50);
        DrawClass.drawSprite(canvas, sc, d2, sc.s2r(sphere.getSp()), sphere.getRadius()*2, 1);
        p.setColor(Color.RED);
        //DrawClass.drawCircle(canvas, sc, sphere, p);
        //DrawClass.drawLine(canvas, sc, l, p);
    }

    public double getSpeed(){
        RealPoint p1 = sc.s2r(sp1);
        RealPoint p2 = sc.s2r(sphere.getSp());
        RealPoint v = p1.findVectro(p2);
        return startSpeed+RealPoint.findDistVector(v)*edinPlusSpeed;
    }

    public ScreenPoint getSp1() {
        return sp1;
    }

    public void setSp1(ScreenPoint sp1) {
        this.sp1 = sp1;
    }

    public ScreenPoint getSp2() {
        return sp2;
    }

    public void setSp2(ScreenPoint sp2) {
        this.sp2 = sp2;
    }

    public SphereScreen getSphere() {
        return sphere;
    }

    public void setSphere(SphereScreen sphere) {
        this.sphere = sphere;
    }
}
