package com.rom.rm.escape.model;

import android.graphics.Bitmap;

import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.util.Painter;
import com.rom.rm.escape.util.RandomNumberGenerator;

public class Enemy {
    private float x,y;
    private int width,height;
    private int VELOCITY_X;
    private Bitmap enemy;

    public Enemy(float x, float y, int width, int height, Bitmap enemy,int VELOCITY_X) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enemy=enemy;
        this.VELOCITY_X=VELOCITY_X;
    }

    public void update (float delta){
        x -= VELOCITY_X;
        if (x < -width) {
            x = GameActivity.WIDTH + 100;
            y = RandomNumberGenerator.getRandInBetween(20,GameActivity.HEIGHT);
        }
    }
    public void render(Painter painter){
        painter.drawImage(enemy,(int)x,(int)y,width,height);
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
