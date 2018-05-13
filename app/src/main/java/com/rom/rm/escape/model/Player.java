package com.rom.rm.escape.model;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.MainThread;
import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.Assets;
import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.SkyWar.GameView;
import com.rom.rm.escape.util.Painter;

/**
 * Created by Rơm on 3/29/2018.
 */

public class Player {
    private float x,y;
    private int width, height;
    private Bitmap player;
    private static final int ACCEL_GRAVITY = 1800;
    private static boolean left=false;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        player= Assets.player;
    }

    public void update(){
        move();
        if (y<0){
            y=0;
        }else if (y> GameActivity.HEIGHT){
            y=GameActivity.HEIGHT;
        }
    }
    private void left(){
        y-=ACCEL_GRAVITY/ GameView.FPS;
    }
    private void right(){
        y+=ACCEL_GRAVITY/GameView.FPS;
    }
    public void render(Painter painter){
         painter.drawImage(player,(int)x,(int)y,width,height);

    }
    public void onTouch(MotionEvent e, int scaleX, int scaleY){
        switch (e.getAction()){
            case MotionEvent.ACTION_UP:
                if (Math.abs(e.getX()-x)<Math.abs(e.getY()-y)){
                    if (e.getY()>y){
                       left=false;
                    }else {
                        left=true;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                x=e.getX();
                y=e.getY();
                break;

        }
    }
    public void move(){
        if (left==true){
            left();
        }else {
            right();
        }
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
