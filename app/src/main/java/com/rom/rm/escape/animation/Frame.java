package com.rom.rm.escape.animation;

import android.graphics.Bitmap;

/**
 * Created by Rơm on 3/29/2018.
 */

public class Frame {
    private Bitmap bitmap;   //hình ảnh của frame
    private double duration; //thời gian frame hiển thị lí tưởng FPS=UPS=60frame/s

    public Frame(Bitmap bitmap, double duration) {
        this.bitmap = bitmap;
        this.duration = duration;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
