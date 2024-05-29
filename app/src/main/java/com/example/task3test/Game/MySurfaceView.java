package com.example.task3test.Game;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.task3test.MainActivity;
import com.example.task3test.Points.ScreenPoint;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread dt;
    private Game game;
    private boolean stop = false;


    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("qqqqqq", "click");
                    game.getCm().setPrevPoint(new ScreenPoint((int) event.getX(), (int) event.getY()));
                    game.pushkaShoot(event.getX(), event.getY());
                    if(game.stopOnP()){
                        stop = true;
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);
                    }

                }


                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    //game.getCm().changeСameraPositionOnSvap(new ScreenPoint((int) event.getX(), (int) event.getY()));
                    if(!game.clickDownEarth(event.getX(), event.getY())){
                        game.changePushkaAngel(event.getX(), event.getY());
                    }
                    game.changeSpeedSnaryad(event.getX(), event.getY());
                    getHandler().sendEmptyMessage(10);


                }
                return true;
            }
        });

    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        double diagonal = Math.sqrt(getWidth()*getWidth() + getHeight()*getHeight());
        game = new Game(0, 1, 80, 80, getWidth(), getHeight(), diagonal, getContext());
        dt = new DrawThread(getContext(), getHolder(), game.getCm(), game);
        dt.start();

    }



    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        dt.setHeight(height);
        dt.setWidth(width);
        game.getCm().getConverter().setSh(height);
        game.getCm().getConverter().setSw(width);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        dt.requestStop();
        boolean retry = true;
        while(retry){
            try {
                dt.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }
    
}
