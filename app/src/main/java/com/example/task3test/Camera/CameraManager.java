package com.example.task3test.Camera;

import android.util.Log;

import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class CameraManager implements ScreenConverterProvider {
    private ScreenConverter sc;
    private ScreenPoint prevPoint = null;
    private double cxS, cyS;
    private RealPoint delta;

    public RealPoint getDelta() {
        return delta;
    }
    private double a = 0;

    public void setDelta(RealPoint delta) {
        this.delta = delta;
    }

    public CameraManager(double cx, double cy, double rw, double rh, int sw, int sh, double diagonal) {

        this.sc = new ScreenConverter(cx, cy, rw, rh, sw, sh, diagonal );
        this.cxS = cx;
        this.cyS = cy;



    }



    public void setPrevPoint(ScreenPoint prevPoint) {
        this.prevPoint = prevPoint;
    }

    @Override
    public ScreenConverter getConverter() {
        return sc;
    }

    public void changeСameraPositionOnSvap(ScreenPoint sp){
        ScreenPoint curPoint = sp;
        RealPoint p1 = getConverter().s2r(curPoint);
        RealPoint p2 = getConverter().s2r(prevPoint);
        delta = p2.minus(p1);
        getConverter().moveCorner(delta);
        prevPoint = curPoint;
    }
    public void changeСameraPositionZaProjectile(ScreenPoint sp, boolean move){
        if(!move){
            return;
        }
        ScreenPoint curPoint = sp;
        RealPoint p1 = getConverter().s2r(curPoint);
        RealPoint p2 = getConverter().s2r(prevPoint);
         delta = p1.minus(p2);
        getConverter().moveCorner(delta);
        Log.i("qqqqqqqqqqqqqqqq", p1+"     " + p2);
        prevPoint = curPoint;
    }
    public void resetPosition(){
        sc.setCx(0);
        sc.setCy(44);
    }








}
