package com.rom.rm.escape.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * Created by Rơm on 3/29/2018.
 */

public class Painter {
    private Canvas canvas;
    private Paint paint;
    private Rect scrRect; //kích thước nguyên thủy của tấm ảnh
    private Rect dstRect;// kích thước muốn hiển thị trong game, data rect kiểu số nguyên
    private RectF dstRectF;//data rect kiểu số thực
    private Picture picture;

    public Canvas getCanvas() {
        return canvas;
    }

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        paint=new Paint();
        scrRect=new Rect();
        dstRect=new Rect();
        dstRectF=new RectF();
    }
    public void setColor(int color){
        paint.setColor(color);
    }
    public void setFont(Typeface font,float size){
        paint.setTypeface(font);
        paint.setTextSize(size);
    }

    //vẽ một chuỗi s tại vị trí có tọa độ x,y
    public void drawString(int x, int y,String s){
        paint.setStyle(Paint.Style.FILL); //Tô đối tượng
        canvas.drawText(s,x,y,paint);
    }
    //vẽ hình chữ nhật tại vị trí có tọa độ x,y, có kích thước sẵn
    public void fillRect(int x, int y, int width,int height){
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x,y,x+width,y+height,paint);
    }
    //vẽ lại
    public void fillRect(Rect rect){
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect,paint);
    }
    //vẽ hình chữ nhật có stroke
    public  void drawRect(int x, int y,int width,int height){
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x,y,x+width,y+height,paint);
    }
    //Vẽ lại
    public void drawRect(Rect rect){
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect,paint);
    }
    //vẽ hình oval
    public void fillOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        dstRectF.set(x, y, x +width, y + height);
        canvas.drawOval(dstRectF, paint);
    }
    //vẽ lại
    public void fillOval(RectF rectangle) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(rectangle, paint);

    }

    public void drawOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        dstRectF.set(x, y, x +width, y + height);
        canvas.drawOval(dstRectF, paint);
    }

    public void drawOval(RectF rectangle) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectangle, paint);

    }

    //vẽ img bitmap
    public void drawImage(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    //scale
    public void drawImage(Bitmap bitmap, int x, int y, int width, int height) {
        scrRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dstRect.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, scrRect, dstRect, paint);
    }


}
