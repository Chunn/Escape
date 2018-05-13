package com.rom.rm.escape.util;

import android.graphics.Color;
import android.graphics.Paint;

import com.rom.rm.escape.SkyWar.Assets;

public class UIText {
    private float x,y;
    private String text;
    private int size=72;
    private Paint paint;

    public UIText(float x, float y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        paint=new Paint();
    }
    public void render(Painter painter){
        paint.setTypeface(Assets.typeface);
        paint.setTextSize(size);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        painter.getCanvas().drawText(text,x,y,paint);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
