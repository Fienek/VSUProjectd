package com.example.task3test.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.view.SurfaceHolder;


import com.example.task3test.Camera.CameraManager;
import com.example.task3test.ChangeSpeedSnaryadView;
import com.example.task3test.DrawClass;
import com.example.task3test.GameObject.item.Earth;
import com.example.task3test.GameObject.item.Line;
import com.example.task3test.GameObject.item.dinamic.Tank;
import com.example.task3test.MapInfo;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenPoint;
import com.example.task3test.GameObject.item.dinamic.Projectile;
import com.example.task3test.GameObject.item.dinamic.Pushka;
import com.example.task3test.R;
import com.example.task3test.RectInfo;
import com.example.task3test.SphereBtn;
import com.example.task3test.StatInfo;

public class DrawThread extends Thread{
    private SurfaceHolder sh;
    private CameraManager cm;
    private volatile boolean running = true;
    private Context context;
    private Bitmap bitmap;
    private Pushka pushka;
    private Earth earth;
    private Tank tank;
    private SphereBtn btn1;
    private SphereBtn btnMinusSpeed;
    private SphereBtn btnPlusSpeed;
    private ChangeSpeedSnaryadView cssv;
    private MapInfo mapInfo;
    private RectInfo rectInfo;
    private Game game;

    private Bitmap bitDirt;
    private BitmapShader bitmapShader;
    private Bitmap bitAir;
    private BitmapShader bitmapShaderAir;
    private Matrix matrix;
    private Matrix matrixAir;
    private RealPoint rp = null;
    private StatInfo statInfo;
    private double b = 0;
    private double d = 0;
    private boolean lose = false;

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public Line ox = new Line(new RealPoint(-1,0), new RealPoint(1, 0));
    public Line oy = new Line(new RealPoint(0,-1), new RealPoint(0, 1));


    private int height;
    private int width;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public DrawThread(Context context, SurfaceHolder sh, CameraManager cm, Game game) {
        //sc = new ScreenConverter(-5,2,10,4,width,height);
        this.sh = sh;
        this.cm = cm;
        this.context = context;
        this.tank = game.getTank();
        this.earth = game.getEarth();
        this.pushka = game.getPushka();
        this.game = game;
        this.btn1 = game.getBtn1();
        this.btnMinusSpeed = game.getBtnMinusSpeed();
        this.btnPlusSpeed = game.getBtnPlusSpeed();
        this.cssv = game.getCssv();
        this.mapInfo = game.getMapInfo();
        this.rectInfo = game.getRectInfo();
        this.bitDirt = BitmapFactory.decodeResource(context.getResources(), R.drawable.dirt3);
        this.bitmapShader = new BitmapShader(bitDirt, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        this.bitAir = BitmapFactory.decodeResource(context.getResources(), R.drawable.air);
        this.bitmapShaderAir = new BitmapShader(bitAir, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        pDirt.setShader(bitmapShader);
        pAir.setShader(bitmapShaderAir);
        this.matrix = new Matrix();
        this.matrixAir = new Matrix();
        this.statInfo = game.getStatInfo();
    }




    public void requestStop(){
        running = false;
    }

    Paint paint = new Paint();
    {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
    }
    Paint pDirt = new Paint();
    {
        pDirt.setColor(0xFFFFFFFF);
    }
    Paint paintCircle = new Paint();
    {
        paintCircle.setStyle(Paint.Style.FILL_AND_STROKE);
        paintCircle.setColor(Color.WHITE);
    }
    Paint paintEarth = new Paint();
    {
        paintEarth.setStyle(Paint.Style.FILL_AND_STROKE);
        paintEarth.setColor(Color.GREEN);

    }
    Paint paintBit = new Paint();

    private Projectile circle;
    @Override
    public void run() {
        while(running){
            Canvas canvas = sh.lockCanvas();
            if(canvas != null){
                try{

                    paint.setColor(Color.CYAN);
                    canvas.drawRect(0, 0, width,height, paint);
                    paint.setColor(Color.GREEN);
                    //cm.setPrevPoint(cm.getConverter().r2s(circle.getRp()));
                    //cm.changeСameraPositionZaProjectile(cm.getConverter().r2s(circle.getRp()), circle.isMoveObject());
                    //drawEarth(canvas);
                    //drawPathAir(canvas);
                    drawPathAir(canvas);
                    drawPathEarth(canvas);
                    if(pushka.getSnaryad() != null){
                        cm.setPrevPoint(cm.getConverter().r2s(pushka.getSnaryad().getRp()));
                        pushka.getSnaryad().update();
                        //DrawClass.drawCircle(canvas, cm.getConverter(), pushka.getSnaryad(), paintCircle);
                        pushka.getSnaryad().draw(canvas, cm.getConverter(), context.getResources().getDrawable(R.drawable.shoot1), context.getResources().getDrawable(R.drawable.shoot2));
                        cm.changeСameraPositionZaProjectile(cm.getConverter().r2s(pushka.getSnaryad().getRp()), !pushka.getSnaryad().isStop());
                        b += cm.getDelta().getX();
                        if(pushka.getSnaryad() != null && pushka.getSnaryad().checkTank(tank.getHitSphere())){
                            pushka.getSnaryad().stop();
                            rp = pushka.getSnaryad().getRp();
                            //rectInfo.setTankText();

                            pushka.setSnaryad(null);
                            tank.stop();
                            tank.setDied(true);
                            rp = null;
                            //cm.resetPosition();
                        }
                        if(pushka.getSnaryad() != null && pushka.getSnaryad().checkEarth(earth.getPointsArr())){
                            pushka.getSnaryad().stop();
                            rp = pushka.getSnaryad().getRp();
                            rectInfo.findDist(rp, tank.getHitSphere().getRpCenter().getRp());
                            pushka.setSnaryad(null);
                            cm.resetPosition();
                            matrix.preTranslate((float) cm.getConverter().rDist2sDist(b), 1);

                        }

                    }

                    DrawClass.drawSprite(canvas, cm.getConverter(),context.getResources().getDrawable(R.drawable.btnvister ), cm.getConverter().s2r(btn1.getSp1()),btn1.getRadius()*2,1 );
                    //DrawClass.drawCircle(canvas, cm.getConverter(), btnMinusSpeed, paintCircle);
                    //DrawClass.drawCircle(canvas, cm.getConverter(), btnPlusSpeed, paintCircle);
                    paint.setColor(Color.BLACK);

                    cssv.draw(canvas, context.getResources().getDrawable(R.drawable.shkala), context.getResources().getDrawable(R.drawable.polzunok));
                    if(tank.getHitSphere().getRpCenter().getRp().getX() - tank.getHitSphere().getRpCenter().getRadius() <= 95){
                        tank.stop();
                        rp = null;
                        //rectInfo.setPText();
                        lose = true;
                    }
                    mapInfo.draw(canvas, tank.getHitSphere().getRpCenter().getRp(), pushka.getSnaryad(),
                            context.getResources().getDrawable(R.drawable.shoot1), context.getResources().getDrawable(R.drawable.tank), tank.isDied(),
                            context.getResources().getDrawable(R.drawable.shoot2), context.getResources().getDrawable(R.drawable.tank2),
                            context.getResources().getDrawable(R.drawable.tankfire));
                    if(rp != null){
                        rectInfo.draw(canvas);
                        d++;
                    }
                    if(d > 125){
                        rp = null;
                        d = 0;
                    }
                    //DrawClass.drawSprite(canvas, cm.getConverter(), context.getDrawable(R.drawable.youwin),
                            // ScreenPoint((int) (cm.getConverter().getSw()*0.5), (int) (cm.getConverter().getSh()*0.5)),
                            //new ScreenPoint((int) (cm.getConverter().getSw()*0.8), (int) (cm.getConverter().getSh()*0.8)), 1500/284);




                    pushka.drawSprite(canvas,cm.getConverter(), pushka.getAngel());

                    /*Drawable d =  context.getResources().getDrawable(R.drawable.pushka, null);
                    d.setBounds(0,0,200,200);
                    canvas.rotate(-30, 20, 150);
                    d.draw(canvas);
                    canvas.rotate(30, 20, 150);*/
                    if(pushka.getSnaryad() == null){
                        statInfo.setPushkaText(""+ (int)pushka.getAngel(), "" +(int) pushka.getSpeed(), 0);
                    } else{
                        statInfo.setPushkaText(""+ (int)pushka.getAngel(), "" +(int) pushka.getSpeed(),(int) pushka.getSnaryad().getRp().getY());
                    }

                    statInfo.draw(canvas);
                    if(tank.isDied()){
                        tank.died(canvas,cm.getConverter(), context.getResources().getDrawable(R.drawable.tankfire));
                        DrawClass.drawSprite(canvas, cm.getConverter(), context.getDrawable(R.drawable.youwin),
                                cm.getConverter().s2r(new ScreenPoint((int) (cm.getConverter().getSw()*0.7), (int) (cm.getConverter().getSh()*0.4))), 30, 1500/284);
                    }else{
                        tank.move(earth.getPointsArr(), canvas, cm.getConverter(), context.getResources().getDrawable(R.drawable.tank), context.getResources().getDrawable(R.drawable.tank2));
                    }
                    if(lose){
                        DrawClass.drawSprite(canvas, cm.getConverter(), context.getDrawable(R.drawable.youlose),
                                cm.getConverter().s2r(new ScreenPoint((int) (cm.getConverter().getSw()*0.7), (int) (cm.getConverter().getSh()*0.4))), 25, 1751/703);
                    }









                    //canvas.drawPath();










                }finally {
                    sh.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void drawPathEarth(Canvas canvas){
        Path path = new Path();
        ScreenPoint start = new ScreenPoint(0, cm.getConverter().getSh() + 10);
        path.moveTo(start.getX(),start.getY());
        ScreenPoint next  = null;
        for (int i = 0; i <earth.getPointsArr().size(); i++) {
            next = cm.getConverter().r2s(new RealPoint(i, earth.getPointsArr().get(i)));
            path.lineTo(next.getX(), next.getY());
        }

        matrix.reset();
        matrix.setScale(1,1);
        matrix.preTranslate((float) -cm.getConverter().rDist2sDist(b), 1);
        bitmapShader.setLocalMatrix(matrix);
        pDirt.setShader(bitmapShader);


        path.close();
        canvas.drawPath(path,pDirt);
        //drawGrass(canvas);

    }
    private void drawGrass(Canvas canvas){
        Path path = new Path();
        RealPoint rpStart = new RealPoint(0,earth.getPointsArr().get(0)+1);
        ScreenPoint start = cm.getConverter().r2s(rpStart);
        path.moveTo(start.getX(),start.getY());
        ScreenPoint next  = null;
        for (int i = 0; i <earth.getPointsArr().size(); i++) {
            next = cm.getConverter().r2s(new RealPoint(i, earth.getPointsArr().get(i)));
            path.lineTo(next.getX(), next.getY());
        }
        ScreenPoint p = cm.getConverter().r2s(new RealPoint(earth.getPointsArr().size(), earth.getPointsArr().get(earth.getPointsArr().size()-1)));
        path.lineTo(p.getX(), p.getY());

        matrix.reset();
        matrix.setScale(1,1);
        matrix.preTranslate((float) -cm.getConverter().rDist2sDist(b), 1);
        bitmapShader.setLocalMatrix(matrix);
        pDirt.setShader(bitmapShader);


        path.close();
        canvas.drawPath(path,paint);
    }

    Paint pAir = new Paint();
    private double c = 0;
    private void drawPathAir(Canvas canvas){
        Path path = new Path();
        ScreenPoint start = new ScreenPoint(0, 0);
        path.moveTo(start.getX(),start.getY());
        ScreenPoint next  = null;
        for (int i = 0; i <earth.getPointsArr().size(); i++) {
            next = cm.getConverter().r2s(new RealPoint(i, earth.getPointsArr().get(i)));
            path.lineTo(next.getX(), next.getY());
        }
        ScreenPoint p = cm.getConverter().r2s(new RealPoint(earth.getPointsArr().size()+20, cm.getConverter().getRh()));
        path.lineTo(p.getX(), 0);

        matrixAir.reset();
        matrixAir.setScale(1,1);
        c-=1;
        matrixAir.preTranslate((float) c, 1);
        bitmapShaderAir.setLocalMatrix(matrixAir);
        pAir.setShader(bitmapShaderAir);


        path.close();
        canvas.drawPath(path,pAir);
    }


    private void drawPushka(Canvas canvas, Pushka p, Bitmap b){
        ScreenPoint sp = cm.getConverter().r2s(p.getRpCenter());
        canvas.drawBitmap(b, sp.getX(), sp.getY(), paint);
    }



    private void drawEarth(Canvas canvas){
        for (int i = 0; i < earth.getPointsArr().size()-1; i++) {
            Line line = new Line(new RealPoint(i, earth.getPointsArr().get(i)), new RealPoint(i+1, earth.getPointsArr().get(i+1)));
            DrawClass.drawLine(canvas, cm.getConverter(), line, paint);
        }
       // drawRect(canvas, new ScreenPoint(0, screenHeight), new ScreenPoint(screenWidth, (int) (screenHeight - y)));
    }









}
