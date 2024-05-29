package com.example.task3test.GameObject.item;

import android.util.Log;

import com.example.task3test.Points.ScreenConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Earth {
    private List<Double> pointsArr;
    private Random random;
    private ScreenConverter sc;
    private  int end = 85;
    private double start;

    public double getStart() {
        return start;
    }

    public List<Double> getPointsArr() {
        return pointsArr;
    }

    public Earth(ScreenConverter sc) {
        this.random = new Random();
        this.sc = sc;
        pointsArr = new ArrayList<>();
        start = sc.getRh()*0.15;
        pointsArr.add(start);
        fillArr();
        Log.i("qqqq", pointsArr.toString());
    }



    private void fillArr(){
        int a = 0;
        for (int i = 0; i < 10; i++) {
            pointsArr.addAll(directly());
        }

        for (int i = 0; i < end; i++) {
            int random= this.random.nextInt(2);
            pointsArr.addAll(directly());
            if(a>1){
                a = 0;
                random = 1;
            }
            Log.i("qqqq", "random = " + random);
            if(random == 1){
                pointsArr.addAll(up());
            } else {
                a++;
                Log.i("qqqqq", "a = " + a);
               pointsArr.addAll(directly());
            }

            /*if(random == 2){
                pointsArr.addAll(up());

            } else if(random == 0){
               pointsArr.addAll(down());

            }else{
                pointsArr.addAll(directly());
            }*/

                //pointsArr.addAll(up());
        }
        pointsArr.add(0.);

    }

    private List<Double> up(){
        int r = random.nextInt(10)*2;
        int r1 = random.nextInt(5);
        int xstart = pointsArr.size()-1;
        double ynew = 0;
        double y = pointsArr.get(xstart) + 2.5*r;
        List<Double> dd = new ArrayList<>();
        for (double i = xstart; i < xstart+9+r1; i+=0.3) {
            ynew = -0.1*r*(i-(xstart+5))*(i-(xstart+5)) + y;
            if(ynew <=1 || y>= sc.sDist2rDist(sc.getSh())/1.75 )
                break;
            dd.add(ynew) ;
        }
        return dd;
    }

    private List<Double> directly(){
        List<Double> dd = new ArrayList<>();
        double a = pointsArr.get(pointsArr.size()-1);
        for (int i = 0; i < 7; i++) {
            dd.add(a);
        }
        return dd;
    }


}

/*int yprev = y;
            if(random < 1 ) {
                yprev-= sc.sy2ry(1);
                if(y >= 0)
                    y = yprev;
            }
            else if(random > 1) {
                yprev += sc.sy2ry(1);
                if (y <= sc.getRh()*0.1)
                    y = yprev;
            }
            private List<Double> down(){
        int xstart = pointsArr.size()-1;
        Log.i("qqqq",xstart+" xstart");
        double y =  pointsArr.get(xstart);
        List<Double> dd = new ArrayList<>();
        for (int i = xstart; i < xstart+10; i++) {
            dd.add(0.1*(i-(xstart+5))*(i-(xstart+5)) + y) ;
        }
        return dd;
    }
     private List<Double> directlyAlways(){
        List<Double> dd = new ArrayList<>();
        double a = pointsArr.get(pointsArr.size()-1);
        for (int i = 0; i < 10; i++) {
            dd.add(a);
        }
        return dd;
    }*/
