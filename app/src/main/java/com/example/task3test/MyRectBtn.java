package com.example.task3test;

import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenPoint;

public class MyRectBtn {
    ScreenPoint rp1, rp2;

    public MyRectBtn(ScreenPoint rp1, ScreenPoint rp2) {
        this.rp1 = rp1;
        this.rp2 = rp2;
    }

    public ScreenPoint getRp1() {
        return rp1;
    }

    public void setRp1(ScreenPoint rp1) {
        this.rp1 = rp1;
    }

    public ScreenPoint getRp2() {
        return rp2;
    }

    public void setRp2(ScreenPoint rp2) {
        this.rp2 = rp2;
    }
}
