package com.example.task3test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.task3test.GameObject.item.Line;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class RectInfo {
    private ScreenPoint sp1, sp2;
    private double height;
    private ScreenConverter sc;
    private String text = "";

    public RectInfo(ScreenPoint sp1, ScreenPoint sp2, double height, ScreenConverter sc) {
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.height = height;
        this.sc = sc;
    }

    public void findDist(RealPoint rp1, RealPoint rp2){
        int dist = (int) (rp2.getX() - rp1.getX());
        Log.i("qqqqqqqqq", "rpS = " + rp1 + "\nrpT = " + rp2);
        if(dist > 0){
            text = "Недолёт на " + dist + " метров";
        } else if(dist<0){
            text = "Перелёт на " + Math.abs(dist) + " метров";
        }
    }

    public void setTankText(){
        text = "Попадание!!!";
    }
    public void setPText(){
        text = "Вы не успели уничтожить танк";
    }

    Paint p = new Paint();
    {
        p.setTextSize(60);
    }

    public void draw(Canvas canvas){
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        ScreenPoint p1 = new ScreenPoint(sp1.getX(),  (sp1.getY()+sc.rDist2sDist((int) (height/2))));
        ScreenPoint p2 = new ScreenPoint(sp2.getX(),  (sp2.getY()-sc.rDist2sDist((int) (height/2))));
        DrawClass.drawRect(canvas, p1, p2, p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        DrawClass.drawRect(canvas, p1, p2, p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(text, sp1.getX()+ sc.rDist2sDist(1), sp1.getY(), p);
    }

}
