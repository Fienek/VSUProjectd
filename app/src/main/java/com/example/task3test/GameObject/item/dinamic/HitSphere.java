package com.example.task3test.GameObject.item.dinamic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.task3test.GameObject.item.Line;
import com.example.task3test.GameObject.item.Sphere;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HitSphere {

    private Sphere leftCenter, rpCenter, rightCenter;
    private Projectile wheelLeft;
    private Projectile wheelRight;
    private int count = 6;
    private double height;
    private double weidth;
    private double radius = 2;
    private double speed;
    private double angel;


    public double getAngel() {
        return angel;
    }

    public HitSphere(RealPoint rpCenter, double height, double weidth, double speed) {
        this.speed = speed;
        this.height = height;
        this.weidth = weidth;
        this.rpCenter = new Sphere(rpCenter, radius);
        wheelLeft = new Projectile(new RealPoint(rpCenter.getX() - weidth/2, rpCenter.getY()-height/2), 1,speed);
        wheelRight = new Projectile(new RealPoint(rpCenter.getX() + weidth/2, rpCenter.getY()-height/2), 1,speed);
        createLeftAndRightCenter();

    }

    private void createLeftAndRightCenter(){
        RealPoint rpwl = new RealPoint(wheelLeft.getRp().getX(), wheelLeft.getRp().getY()+height/2);
        RealPoint rpwr = new RealPoint(wheelRight.getRp().getX(), wheelRight.getRp().getY()+height/2);
        RealPoint v = rpwl.findVectro(rpwr);
        double sizeV = Math.sqrt(v.getX()*v.getX()+v.getY()*v.getY());
        v.div(sizeV);
        RealPoint rpl = v.sub(-0.33);
        RealPoint rpr = v.sub(0.33);

        leftCenter = new Sphere(new RealPoint(rpl.getX(), rpl.getY()).plus(rpCenter.getRp()), radius);
        rightCenter = new Sphere(new RealPoint(rpr.getX(), rpr.getY()).plus(rpCenter.getRp()), radius);
    }

    public void update(List<Double> listArr){
        wheelRight.updateWheel(listArr);
        wheelLeft.updateWheel(listArr);
        //RealPoint oldCenter = rpCenter.getRp();
        rpCenter.setRp(newCenter());
        //RealPoint delt = rpCenter.minus(oldCenter);
        createLeftAndRightCenter();
        RealPoint rp  = wheelRight.getRp().findVectro(wheelLeft.getRp());
        RealPoint rp2 = rpCenter.getRp().findVectro(new RealPoint(rpCenter.getRp().getX() -1,rpCenter.getRp().getY()));
        if(rp.getY() < rp2.getY() ){
            angel = -(RealPoint.findCorner(rp, rp2));
        }else {
            angel = RealPoint.findCorner(rp, rp2);
        }


    }
    //arr.get(i).setRp(rp.plus(delt));

    private RealPoint newCenter(){
        double x = (wheelLeft.getRp().getX() + wheelRight.getRp().getX())/2;
        double y = (wheelLeft.getRp().getY() + wheelRight.getRp().getY())/2 + height/2;
        return new RealPoint(x,y);
    }
    public boolean findMinSize(Snaryad s){
        RealPoint a = leftCenter.getRp().minus(s.getRp());
        RealPoint b = rpCenter.getRp().minus(s.getRp());
        RealPoint c = rightCenter.getRp().minus(s.getRp());
        List<Double> test = new ArrayList<>();
        test.add(Math.sqrt(a.getX()*a.getX() + a.getY()*a.getY()));
        test.add(Math.sqrt(b.getX()*b.getX() + b.getY()*b.getY()));
        test.add(Math.sqrt(c.getX()*c.getX() + c.getY()*c.getY()));
        double min = test.get(0);
        for (int i = 1; i < test.size(); i++) {
            if(test.get(i) < min)
                min = test.get(i);
        }

        Log.i("qqqqqq3", "min = " + min + " r = " + (radius/3 + s.getRadius()/3));
        if(min < radius + s.getRadius()){
            return  true;
        }

        return false;
    }


    private double findCorner(){
        RealPoint v1 = wheelRight.getRp().minus(wheelLeft.getRp());
        RealPoint v2 = new RealPoint(1,0);
        double a = v1.getX()*v2.getX() + v1.getY()*v2.getY();
        double b = Math.sqrt(v1.getX()* v1.getX() + v1.getY()*v1.getY());
        double c = Math.sqrt(v2.getX()* v2.getX() + v2.getY()*v2.getY());
        double cos = a/(b*c);
        double resutl = Math.toDegrees(Math.acos(cos));
        Log.i("qqqqqqqq", "grad = "  + resutl);
        return resutl;

    }

    public void setRpCenter(RealPoint rpCenter) {
        this.rpCenter .setRp(rpCenter);
    }



    public void draw(Canvas canvas, ScreenConverter sc){
        drawCircle(canvas, sc, wheelRight);
        drawCircle(canvas, sc, wheelLeft);
        paint.setColor(Color.RED);
        Line l = new Line(new RealPoint(wheelLeft.getRp().getX(), wheelLeft.getRp().getY()), new RealPoint(wheelRight.getRp().getX(), wheelRight.getRp().getY()));
        drawLine(canvas, sc, l);
        drawCircle(canvas, sc, rpCenter);
        paint.setColor(Color.WHITE);
        drawCircle(canvas, sc, leftCenter);
        drawCircle(canvas, sc, rightCenter);
        RealPoint rpwl = new RealPoint(wheelLeft.getRp().getX(), wheelLeft.getRp().getY()+height/2);
        RealPoint rpwr = new RealPoint(wheelRight.getRp().getX(), wheelRight.getRp().getY()+height/2);
        Line l1 = new Line(rpwl, rpwr);
        drawLine(canvas, sc, l1);
    }
    /*
    if(corn>0){
            canvas.rotate((float) corn);
            drawLine(canvas, sc, new Line(new RealPoint(rpCenter.getX()-weidth/2, rpCenter.getY()+height/2), new RealPoint(rpCenter.getX()+weidth/2, rpCenter.getY()+height/2)));
            canvas.rotate((float) -corn);
        }else {
            drawLine(canvas, sc, new Line(new RealPoint(rpCenter.getX()-weidth/2, rpCenter.getY()+height/2), new RealPoint(rpCenter.getX()+weidth/2, rpCenter.getY()+height/2)));
        }
     */




    Paint paint;
    {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    private void drawCircle(Canvas canvas, ScreenConverter sc, Projectile p){
        ScreenPoint sp = sc.r2s(p.getRp());
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }
    private void drawCircle(Canvas canvas, ScreenConverter sc, Sphere p){
        ScreenPoint sp = sc.r2s(p.getRp());
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }
    private void drawLine(Canvas canvas, ScreenConverter sc, Line l){
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        canvas.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), paint);
    }

    private void drawRect(Canvas canvas, ScreenConverter sc, RealPoint rp1, RealPoint rp2){
        ScreenPoint p1 = sc.r2s(rp1);
        ScreenPoint p2 = sc.r2s(rp2);
        canvas.drawRect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), paint);
    }

    public void changeSpeed(double nSpeed){
        wheelLeft.setSpeed(nSpeed);
        wheelRight.setSpeed(nSpeed);
    }


    public Sphere getRpCenter() {
        return rpCenter;
    }
}
