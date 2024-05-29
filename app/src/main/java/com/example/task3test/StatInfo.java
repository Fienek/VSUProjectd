package com.example.task3test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class StatInfo {
    private ScreenPoint sp1, sp2;
    private double height;
    private ScreenConverter sc;
    private String text1 = "";
    private String text2 = "";
    private String text3 = "";

    public StatInfo(ScreenPoint sp1, ScreenPoint sp2, double height, ScreenConverter sc) {
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.height = height;
        this.sc = sc;
    }


    public void setPushkaText(String angle, String speed, int heightS){
        text1 = "Скорость = " + speed;
        text2 = "Угол = " + angle;
        text3 = "Высота снаряда " + heightS;
    }

    Paint p = new Paint();
    {
        p.setTextSize(60);
    }

    public void draw(Canvas canvas){
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(text1, sp1.getX()+ sc.rDist2sDist(1), sp1.getY(), p);
        canvas.drawText(text2, sp1.getX()+ sc.rDist2sDist(1), sp1.getY() + sc.rDist2sDist(3), p);
        canvas.drawText(text3, sp1.getX()+ sc.rDist2sDist(1), sp1.getY() + sc.rDist2sDist(6), p);
    }

}
