package com.example.task3test.GameObject.item.dinamic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;


import com.example.task3test.GameObject.item.Line;
import com.example.task3test.GameObject.item.Sphere;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class Pushka {
        private static final double MAX_ANGLE = 90;
        private static final double MIN_ANGLE = -90;
        private Drawable gunV;
        private Drawable gunN;
        private RealPoint rpSstart;
        private double da = 404/128;
        private double d2 = 112/16;
        private double m = 5;
        private Sphere sphere;
        private Snaryad snaryad;
        private RealPoint rpS;
        private double height;
        private double weight;
        private RealPoint rpCenter;
        private double angel = 0;
        private double speed;
        private  ScreenConverter sc;

        public Pushka(ScreenConverter sc, RealPoint rpCenter, double weight, double height, Drawable gunV, Drawable gunN) {
                this.sc = sc;
                this.weight = weight*2;
                this.height = sc.sDist2rDist((int) (sc.rDist2sDist(weight)/da))*2;
                this.rpCenter = new RealPoint(rpCenter.getX()+weight/2, rpCenter.getY()+height/3);
                Log.i("Sprite",""+ rpCenter.getY());
                Log.i("Sprite",""+ this.rpCenter.getY());
                this.sphere = new Sphere(rpCenter, weight+weight*0.3);
                this.gunV = gunV;
                this.gunN = gunN;
                changeAngel();
                startAngle();

                //new RealPoint(rpCenter.getX()+weight/2, rpCenter.getY());
//new MyRect(new RealPoint(rpCenter.getX()-weight/2, rpCenter.getY()+height/2),
//                        new RealPoint(rpCenter.getX()+weight/2, rpCenter.getY()-height/2));
                this.speed = 24;
        }


        Paint p = new Paint();
        {
                p.setStyle(Paint.Style.FILL_AND_STROKE);
                p.setColor(Color.BLUE);
        }

        public void changeAngel(){
                double a = Math.toRadians(angel);
                double x = rpCenter.getX() + sphere.getRadius() * Math.cos(a);
                double y = rpCenter.getY() + sphere.getRadius() * Math.sin(a);
                rpS = new RealPoint(x,y);
        }
        public void startAngle(){
                double a = Math.toRadians(angel);
                double x = rpCenter.getX() + sphere.getRadius() * Math.cos(a);
                double y = rpCenter.getY() + sphere.getRadius() * Math.sin(a);
                rpSstart = new RealPoint(x,y);
        }


        public void draw(Canvas canvas, ScreenConverter sc){
                drawCircle(canvas, sc, sphere);
                p.setColor(Color.BLACK);
                Line l = new Line(rpCenter, rpS);
                drawLine(canvas, sc, l);
                drawCircle(canvas, sc, new Sphere(rpS, 1));
                p.setColor(Color.BLUE);
        }
        public void drawSprite(Canvas canvas, ScreenConverter sc, double angel){
                double heighv = sc.rDist2sDist(weight)/d2/2;
                double weidhv = (double) sc.rDist2sDist(weight)/2 ;
                ScreenPoint center = sc.r2s(rpCenter);
                gunV.setBounds((int) (center.getX() - weidhv + weidhv/3.5), (int) (center.getY()-heighv - heighv/2), (int) (center.getX()+weidhv+ weidhv/3.5), (int) (center.getY()+heighv));
                canvas.rotate((float) -angel, (float) (center.getX() - weidhv/4), center.getY());
                gunV.draw(canvas);
                canvas.rotate((float) angel, (float) (center.getX()- weidhv/4), center.getY());

                double heightp = sc.rDist2sDist(height)/2;
                double weidhtp = (double) sc.rDist2sDist(weight)/2 ;
                gunN.setBounds((int) (center.getX() - weidhtp), (int) (center.getY()-heightp), (int) (center.getX()+weidhtp), (int) (center.getY()+heightp));
                gunN.draw(canvas);
        }


        private void drawCircle(Canvas canvas, ScreenConverter sc, Sphere s){
                ScreenPoint sp = sc.r2s(s.getRp());
                double r = sc.rDist2sDist(s.getRadius());
                canvas.drawCircle((float) sp.getX(), (float) sp.getY(), (float) r, p);
        }

        private void drawRect(Canvas canvas, ScreenConverter sc, RealPoint rp1, RealPoint rp2){
                ScreenPoint sp1 = sc.r2s(rp1);
                ScreenPoint sp2 = sc.r2s(rp2);
                canvas.drawRect(sp1.getX(), sp1.getY(), sp2.getX(), sp2.getY(), p);
        }
        private void drawLine(Canvas canvas, ScreenConverter sc, Line l){
                ScreenPoint p1 = sc.r2s(l.getP1());
                ScreenPoint p2 = sc.r2s(l.getP2());
                canvas.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p);
        }


        public double getSpeed() {
                return speed;
        }

        public void plusSpeed() {
                this.speed++;
        }
        public void minusSpeed() {
                this.speed--;
        }

        public Snaryad getSnaryad() {
                return snaryad;
        }

        public void setSnaryad(Snaryad snaryad) {
                this.snaryad = snaryad;
        }

        public RealPoint getRpS() {
                return rpS;
        }

        public void setRpS(RealPoint rpS) {
                this.rpS = rpS;
        }

        public double getHeight() {
                return height;
        }

        public void setHeight(double height) {
                this.height = height;
        }

        public double getWeight() {
                return weight;
        }

        public void setWeight(double weight) {
                this.weight = weight;
        }

        public RealPoint getRpCenter() {
                return rpCenter;
        }

        public void setRpCenter(RealPoint rpCenter) {
                this.rpCenter = rpCenter;
        }

        public double getAngel() {
                return angel;
        }

        public void setAngel(double angel) {
                this.angel =angel;
                changeAngel();
        }

        public Sphere getSphere() {
                return sphere;
        }

        public void setSphere(Sphere sphere) {
                this.sphere = sphere;
        }

        public void setSpeed(double speed) {
                this.speed = speed;
        }

        public RealPoint getRpSstart() {
                return rpSstart;
        }

        public void setRpSstart(RealPoint rpSstart) {
                this.rpSstart = rpSstart;
        }
}
