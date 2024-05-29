package com.example.task3test.Game;

import android.content.Context;
import android.util.Log;

import com.example.task3test.Camera.CameraManager;
import com.example.task3test.ChangeSpeedSnaryadView;
import com.example.task3test.GameObject.item.Earth;
import com.example.task3test.GameObject.item.SphereScreen;
import com.example.task3test.GameObject.item.dinamic.Pushka;
import com.example.task3test.GameObject.item.dinamic.Snaryad;
import com.example.task3test.GameObject.item.dinamic.Tank;
import com.example.task3test.MapInfo;
import com.example.task3test.Points.RealPoint;
import com.example.task3test.Points.ScreenConverter;
import com.example.task3test.Points.ScreenPoint;
import com.example.task3test.R;
import com.example.task3test.RectInfo;
import com.example.task3test.SphereBtn;
import com.example.task3test.StatInfo;

public class Game {
    private Context context;
    private CameraManager cm;
    private Pushka pushka;
    private Earth earth;
    private Tank tank;
    private SphereBtn btn1;
    private SphereBtn btnMinusSpeed;
    private SphereBtn btnPlusSpeed;
    private ChangeSpeedSnaryadView cssv;
    private MapInfo mapInfo;
    private RectInfo rectInfo;
    private StatInfo statInfo;
    private boolean isStop = false;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public Game (int cx, int cy, double rw, double rh, int sw, int sh, double diagonal, Context context){
        this.context = context;
        this.cm = new CameraManager(cx,cy,rw,rh,sw,sh,diagonal);
        this.earth = new Earth(cm.getConverter());
        //this.pushka = new Pushka(new RealPoint(2, earth.getPointsArr().get(2)+10), BitmapFactory.decodeResource(context.getResources(), R.drawable.pushka), cm.getConverter());
        this.tank = new Tank(new RealPoint(earth.getPointsArr().size()-25, earth.getPointsArr().get(earth.getPointsArr().size()-26)), 4, 8, -1);
        //this.tank = new Tank(new RealPoint(50, earth.getPointsArr().get(50)), 4, 8, -1);
        this.pushka = new Pushka(cm.getConverter(),new RealPoint(6,earth.getPointsArr().get(6)),  10, 10,
                context.getResources().getDrawable(R.drawable.dsadsad),  context.getResources().getDrawable(R.drawable.dsadsadasd));
        this.btn1 = new SphereBtn(new ScreenPoint((int) (sw*0.85), (int) (sh*0.85)), 4);
        this.btnMinusSpeed = new SphereBtn(new ScreenPoint((int) (sw*0.1), (int) (sh*0.85)), 4);
        this.btnPlusSpeed = new SphereBtn(new ScreenPoint((int) (sw*0.4), (int) (sh*0.85)), 4);
        this.cssv = new ChangeSpeedSnaryadView(new ScreenPoint((int) (sw*0.1), (int) (sh*0.90)), new ScreenPoint((int) (sw*0.4), (int) (sh*0.90)),7, cm.getConverter());
        this.mapInfo = new MapInfo(new ScreenPoint((int) (sw*0.5), (int) (sh*0.15)), new ScreenPoint((int) (sw*0.9), (int) (sh*0.15)), 8, cm.getConverter(), earth.getPointsArr().size());
        this.rectInfo = new RectInfo(new ScreenPoint((int) (sw*0.53), (int) (sh*0.4)), new ScreenPoint((int) (sw*0.86), (int) (sh*0.4)),6, cm.getConverter());
        this.statInfo = new StatInfo(new ScreenPoint((int) (sw*0.005), (int) (sh*0.05)), new ScreenPoint((int) (sw*0.2), (int) (sh*0.3)),6, cm.getConverter());
    }
    //new RealPoint(earth.getPointsArr().get(earth.getPointsArr().size()-10), earth.getPointsArr().get(earth.getPointsArr().size()-10))
    public void createSnaryad(){
        if(pushka.getSnaryad() == null) {
            pushka.setSnaryad(new Snaryad(pushka.getSpeed(), pushka.getAngel(), pushka.getRpS(), 2));
            pushka.getSnaryad().setCreate(true);
        }
    }

    public boolean clickDownEarth(double x, double y){
        RealPoint p = cm.getConverter().s2r(new ScreenPoint((int) x, (int) y));
        if(p.getY() <= earth.getPointsArr().get(0)){
            return true;
        }
        return false;
    }
    public boolean stopOnP(){
        if(tank.isStop()){
            setStop(true);
            return true;
        }
        return false;
    }


    public void changePushkaAngel(double x, double y){
        RealPoint rp = getCm().getConverter().s2r(new ScreenPoint((int) x, (int) y));
        RealPoint rp0 = pushka.getRpCenter().findVectro(pushka.getRpSstart());
        RealPoint rpv = pushka.getRpCenter().findVectro(rp);
        double a = RealPoint.findCorner(rp0, rpv);
        if(a<=90) {
            if (rp.getY() < pushka.getRpCenter().getY()) {
                getPushka().setAngel(-a);
            } else {
                getPushka().setAngel(a);
            }
        }

        Log.i("qqqqqq0", "a = " + a);
    }
    public void changeSpeedSnaryad(double x, double y){
       if(cssv.isClick(x, y)){
            double yn = cssv.getSphere().getSp().getY();
            cssv.setSphere(new SphereScreen(new ScreenPoint((int) x, (int) yn), cssv.getSphere().getRadius()));
            pushka.setSpeed(cssv.getSpeed());
       }
    }
    public void pushkaShoot(double x, double y){
        if(btn1.clickOnBtn(x, y, cm.getConverter())){
            createSnaryad();
        }
    }
    public Pushka getPushka() {
        return pushka;
    }

    public Earth getEarth() {
        return earth;
    }



    public Tank getTank() {
        return tank;
    }

    public CameraManager getCm() {
        return cm;
    }

    public void setPushka(Pushka pushka) {
        this.pushka = pushka;
    }

    public void setEarth(Earth earth) {
        this.earth = earth;
    }



    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public SphereBtn getBtn1() {
        return btn1;
    }

    public SphereBtn getBtnMinusSpeed() {
        return btnMinusSpeed;
    }

    public SphereBtn getBtnPlusSpeed() {
        return btnPlusSpeed;
    }

    public ChangeSpeedSnaryadView getCssv() {
        return cssv;
    }

    public MapInfo getMapInfo() {
        return mapInfo;
    }

    public RectInfo getRectInfo() {
        return rectInfo;
    }

    public StatInfo getStatInfo() {
        return statInfo;
    }
}


