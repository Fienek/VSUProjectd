package com.example.task3test.GameObject.item;

import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class Line {
    private RealPoint p1, p2;
    private ScreenPoint s1, s2;

    public Line(RealPoint p1, RealPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public Line(ScreenPoint p1, ScreenPoint p2) {
        this.s1 = p1;
        this.s2 = p2;
    }

    public RealPoint getP1() {
        return p1;
    }

    public void setP1(RealPoint p1) {
        this.p1 = p1;
    }

    public RealPoint getP2() {
        return p2;
    }

    public void setP2(RealPoint p2) {
        this.p2 = p2;
    }

    public ScreenPoint getS1() {
        return s1;
    }

    public ScreenPoint getS2() {
        return s2;
    }

    public double getSize(){
        RealPoint rp = p2.minus(p1);
        return Math.sqrt(rp.getX()*rp.getX() + rp.getY() * rp.getY());
    }
}
