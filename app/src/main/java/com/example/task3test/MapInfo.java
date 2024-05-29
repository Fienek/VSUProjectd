package com.example.task3test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.task3test.GameObject.item.SphereScreen;
import com.example.task3test.GameObject.item.dinamic.Snaryad;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class MapInfo {
    private ScreenPoint sp1, sp2;
    private double height;
    private double size;
    private ScreenConverter sc;
    private SphereScreen sphereTank;
    private SphereScreen sphereSnaryad;
    private double earthSize;
    private boolean da = false;
    private double b = 0;


    public MapInfo(ScreenPoint sp1, ScreenPoint sp2, double height, ScreenConverter sc, double earthSize) {
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.height = height;
        RealPoint rp1 = sc.s2r(sp1);
        RealPoint rp2 = sc.s2r(sp2);
        this.size = RealPoint.findDistVector(rp1.findVectro(rp2));
        this.sc = sc;
        this.earthSize = earthSize;
        this.sphereTank = new SphereScreen(sp2, height*0.75);
        this.sphereSnaryad = new SphereScreen(sp2, height/2);
    }

    Paint p = new Paint();
    {
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void changeSpherePosTank(RealPoint rp){
        RealPoint p1 = sc.s2r(sp2);
        RealPoint p2 = sc.s2r(sp1);
        double one = size/earthSize;
        double a = one * rp.getX();
        RealPoint p = new RealPoint(p2.getX()+a,p1.getY());
        sphereTank.setSp(sc.r2s(p));
    }
    public void changeSpherePosSnaryad(RealPoint rp){
        RealPoint p1 = sc.s2r(sp2);
        RealPoint p2 = sc.s2r(sp1);
        double one = size/earthSize;
        double a = one * rp.getX();
        RealPoint p = new RealPoint(p2.getX()+a,p1.getY());
        sphereSnaryad.setSp(sc.r2s(p));
    }


    public SphereScreen getSphereSnaryad() {
        return sphereSnaryad;
    }

    public void setSphereSnaryad(SphereScreen sphereSnaryad) {
        this.sphereSnaryad = sphereSnaryad;
    }

    public void draw(Canvas canvas, RealPoint tankCenter, Snaryad snaryad, Drawable d1, Drawable d2, boolean tankDied, Drawable d11, Drawable d22, Drawable d111){

        changeSpherePosTank(tankCenter);


        ScreenPoint p1 = new ScreenPoint(sp1.getX()-sc.rDist2sDist(height/2),  (sp1.getY()+sc.rDist2sDist(height/2)));
        ScreenPoint p2 = new ScreenPoint(sp2.getX()+sc.rDist2sDist(height/2),  (sp2.getY()-sc.rDist2sDist(height/2)));

        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.rgb(115, 66, 34));
        DrawClass.drawRect(canvas, p1, new ScreenPoint(sp2.getX()+sc.rDist2sDist(height/2)-1, sp2.getY()), p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        DrawClass.drawRect(canvas, p1, p2, p);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.RED);
        //DrawClass.drawCircle(canvas, sc, sphereTank, p);
        if(!tankDied){
            if(da){
                DrawClass.drawSprite(canvas, sc, d2, sc.s2r(sphereTank.getSp()), sphereTank.getRadius()*2, 1315/381);
            }else{
                DrawClass.drawSprite(canvas, sc, d22, sc.s2r(sphereTank.getSp()), sphereTank.getRadius()*2, 1315/381);
            }
        }else {
            DrawClass.drawSprite(canvas, sc, d111, sc.s2r(sphereTank.getSp()), sphereTank.getRadius()*2, 1195/377);
        }


        if(snaryad != null){
            p.setColor(Color.WHITE);
            changeSpherePosSnaryad(snaryad.getRp());
            //DrawClass.drawCircle(canvas, sc, sphereSnaryad, p);
            if(!da){
                DrawClass.drawSprite(canvas, sc, d1, sc.s2r(sphereSnaryad.getSp()), sphereSnaryad.getRadius(), 89/26);
            }else{
                DrawClass.drawSprite(canvas, sc, d11, sc.s2r(sphereSnaryad.getSp()), sphereSnaryad.getRadius(), 89/26);
            }

            p.setColor(Color.BLACK);
        }
        if (b >= 25) {
            if(da){
                da = false;
                b = 0;
            } else {
                da = true;
                b = 0;
            }
        }
        b++;
       // Log.i("wqeqw", da + " " + b);
    }
}
