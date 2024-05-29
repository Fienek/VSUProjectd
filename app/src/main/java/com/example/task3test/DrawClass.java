package com.example.task3test;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.task3test.GameObject.item.Line;
import com.example.task3test.GameObject.item.SphereScreen;
import com.example.task3test.GameObject.item.Sphere;
import com.example.task3test.GameObject.item.dinamic.Projectile;
import com.example.task3test.GameObject.item.dinamic.Snaryad;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class DrawClass {

    public static void drawLine(Canvas canvas, ScreenConverter sc, Line l, Paint paint){
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        canvas.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), paint);
    }

    public static void drawRect(Canvas canvas, ScreenPoint p1, ScreenPoint p2, Paint paint){
        canvas.drawRect(p1.getX(),p1.getY(),p2.getX(),p2.getY(), paint);
    }

    public static void drawCircle(Canvas canvas, ScreenConverter sc, Projectile p, Paint paint){
        ScreenPoint sp = sc.r2s(p.getRp());
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }
    public static void drawCircle(Canvas canvas, ScreenConverter sc, SphereScreen p, Paint paint){
        ScreenPoint sp = p.getSp();
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }
    public static void drawCircle(Canvas canvas, ScreenConverter sc, Sphere p, Paint paint){
        ScreenPoint sp = sc.r2s(p.getRp());
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }
    public  static void drawCircle(Canvas canvas, ScreenConverter sc, SphereBtn p, Paint paint){
        ScreenPoint sp = p.getSp1();
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }
    public  static void drawCircle(Canvas canvas, ScreenConverter sc, Snaryad p, Paint paint){
        ScreenPoint sp = sc.r2s(p.getRp());
        double r = sc.rDist2sDist(p.getRadius());
        canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, paint);
    }

    public static void drawSprite(Canvas canvas, ScreenConverter sc, Drawable sprite, RealPoint rpCenter, double width, double otnosh){
        double widthS = sc.rDist2sDist(width);
        double heightS = widthS/otnosh;
        ScreenPoint center = sc.r2s(rpCenter);
       // sprite.setBounds((int) (center.getX() - widthS/2), (int) (center.getY()-heightS/2), (int) (center.getX()+widthS/2), (int) (center.getY()-heightS/2));
        //sprite.setBounds(0,0, 100, 100);
        //sprite.draw(canvas);
        double heightp = sc.rDist2sDist(width)/otnosh/2;
        double weidhtp = (double) sc.rDist2sDist(width)/2 ;
        Log.i("Sprite", heightp + "");
        sprite.setBounds((int) (center.getX() - weidhtp), (int) (center.getY()-heightp), (int) (center.getX()+weidhtp), (int) (center.getY()+heightp));
        sprite.draw(canvas);
    }
    public static void drawSprite(Canvas canvas, ScreenConverter sc, Drawable sprite, ScreenPoint sp1, ScreenPoint sp2, double otnosh){
        // sprite.setBounds((int) (center.getX() - widthS/2), (int) (center.getY()-heightS/2), (int) (center.getX()+widthS/2), (int) (center.getY()-heightS/2));
        //sprite.setBounds(0,0, 100, 100);
        //sprite.draw(canvas);
        //Log.i("Sprite", heightp + "");
        sprite.setBounds((int) (sp1.getX()), (int) (sp1.getY()), (int) (sp2.getX()), (int) (sp2.getY()));
        sprite.draw(canvas);
    }
    public static void drawSprite(Canvas canvas, ScreenConverter sc, Drawable sprite, RealPoint rpCenter, double width, double otnosh, double angle){
        double widthS = sc.rDist2sDist(width);
        double heightS = widthS/otnosh;
        ScreenPoint center = sc.r2s(rpCenter);
        // sprite.setBounds((int) (center.getX() - widthS/2), (int) (center.getY()-heightS/2), (int) (center.getX()+widthS/2), (int) (center.getY()-heightS/2));
        //sprite.setBounds(0,0, 100, 100);
        //sprite.draw(canvas);
        Log.i("Sprite", angle + "");
        double heightp = sc.rDist2sDist(width)/otnosh/2;
        double weidhtp = (double) sc.rDist2sDist(width)/2 ;
        sprite.setBounds((int) (center.getX() - weidhtp), (int) (center.getY()-heightp), (int) (center.getX()+weidhtp), (int) (center.getY()+heightp));
        canvas.rotate((float) angle, center.getX(), center.getY());
        sprite.draw(canvas);
        canvas.rotate((float) -angle, center.getX(), center.getY());
    }
}
