package com.example.task3test.Points;

import android.util.Log;

public class ScreenConverter {
    private double cx, cy, rw, rh;
    private int sw, sh;
    private double realToScreenDiagonal;
    private RealPoint centerOfReal;

    public ScreenConverter(double cx, double cy, double rw, double rh, int sw, int sh, double diagonalScreen) {
        this.cx = cx;
        this.cy = cy;
        this.rw = rw;
        this.rh = rh;
        this.sw = sw;
        this.sh = sh;

        double diagonalReal = Math.sqrt(rw*rw + rh*rh);
        realToScreenDiagonal = diagonalReal /diagonalScreen;
        centerOfReal = findCenterOfReal(sw, sh);
        this.cy = centerOfReal.getY();
        this.cx = centerOfReal.getX();
        Log.i("qqqq", "cx = " + cx + " cy = " + cy);
    }

    private RealPoint  findCenterOfReal(int sw, int sh){
        int y = sh;
        int x = 0;
        Log.i("qqqq", "y = " + y + " x = " + x  );

        double xn = (0) * realToScreenDiagonal;
        double yn = (sh) * realToScreenDiagonal;
        return new RealPoint(xn, yn);
    }

    private double realWidth() {
        return realToScreenDiagonal * sw;
    }
    private double realHeight() {
        return realToScreenDiagonal * sh;

    }

    public ScreenPoint r2s(RealPoint p){
        //double x = (p.getX() - cx) / realToScreenDiagonal;
        //double y = (cy - p.getY()) / realToScreenDiagonal;
        double x = ((p.getX() - cx) / (realWidth())) * sw;
        double y = ((cy - p.getY()) / (realHeight())) * sh;

        return new ScreenPoint((int) x ,(int) y);
    }
    public int rx2sx(double rx){
       int x =  (int) ((rx - cx) / (realWidth())) * sw;
       return x;
    }

    public RealPoint s2r(ScreenPoint p){
        double x = cx + p.getX() * realToScreenDiagonal;
        double y = cy - p.getY() * realToScreenDiagonal;

        return new RealPoint(x, y);
    }



    public int rDist2sDist(double d){
        return (int) (d/realToScreenDiagonal);
    }
    public double sDist2rDist(int d){
        return  d*realToScreenDiagonal;
    }
    public double rv2sv(int v){
        Log.i("qqqq", String.valueOf(v * realToScreenDiagonal));
        return  v * realToScreenDiagonal;
    }

    public void moveCorner(RealPoint delta){
        cx += delta.getX();
        //cy += delta.getY();
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getRw() {
        return rw;
    }

    public void setRw(double rw) {
        this.rw = rw;
    }

    public double getRh() {
        return rh;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }
    public RealPoint getCRP(){
        return centerOfReal;
    }


}
