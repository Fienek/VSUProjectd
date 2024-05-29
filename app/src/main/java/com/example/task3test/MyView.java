package com.example.task3test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

   /* private Line ox = new Line(new RealPoint(-1,0), new RealPoint(1, 0));
    private Line oy = new Line(new RealPoint(0,-1), new RealPoint(0, 1));
    private ScreenConverter sc = new ScreenConverter(-2,2,4,4,getWidth(), getHeight(),10);

    public MyView(Context context) {
        super(context);

    }


    Paint p = new Paint();
    {
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.GREEN);

    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        sc.setSw(getWidth());
        sc.setSh(getHeight());

        drawLine(canvas, sc, ox);
        drawLine(canvas, sc, oy);
    }

    private void drawLine(Canvas canvas, ScreenConverter sc, Line l){
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        canvas.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p);
    }

    */
}


