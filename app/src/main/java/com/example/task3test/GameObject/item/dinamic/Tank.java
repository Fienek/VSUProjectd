package com.example.task3test.GameObject.item.dinamic;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.task3test.DrawClass;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;

import java.util.List;

public class Tank {
    private HitSphere hitSphere;
    private RealPoint rpCenter;
    private double height;
    private double weidht;
    private double speed;
    private double d = 1315/381;
    private boolean isStop = false;
    boolean da = true;
    private int b = 0;
    private boolean died = false;

    public double getWeidht() {
        return weidht;
    }

    public boolean isDied() {
        return died;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public Tank(RealPoint rpStart, double height, double weidht, double speed) {
        this.speed = speed;
        this.weidht = weidht;
        this.height = weidht/d;
        this.rpCenter = new RealPoint(weidht/2, height/2);
        this.hitSphere = new HitSphere(rpStart, height, weidht, speed);
    }
    public void stop(){
        isStop = true;
        hitSphere.changeSpeed(0);
    }


    public HitSphere getHitSphere() {
        return hitSphere;
    }

    public void move(List<Double> earth, Canvas canvas, ScreenConverter sc, Drawable d, Drawable d2){
        hitSphere.update(earth);
        //hitSphere.draw(canvas, sc);
        RealPoint rp = hitSphere.getRpCenter().getRp();
        if(da){
            DrawClass.drawSprite(canvas, sc, d, new RealPoint(rp.getX()-3, rp.getY()), weidht*3, 1315/381, hitSphere.getAngel());
        }else {
            DrawClass.drawSprite(canvas, sc, d2, new RealPoint(rp.getX()-3, rp.getY()), weidht*3, 1315/381, hitSphere.getAngel());
        }
        if(!isStop){
            b++;
        }

        if (b >= 25) {
            if(da){
                da = false;
            } else
                da = true;
            b = 0;
        }

    }
    public void died(Canvas canvas, ScreenConverter sc, Drawable d){
        //hitSphere.update(earth);
        RealPoint rp = hitSphere.getRpCenter().getRp();
        DrawClass.drawSprite(canvas, sc, d, new RealPoint(rp.getX()-3, rp.getY()), weidht*3, 1195/377, hitSphere.getAngel());
        //hitSphere.draw(canvas, sc);
    }



}
