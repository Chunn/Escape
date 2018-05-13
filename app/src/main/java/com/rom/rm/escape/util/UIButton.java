package com.rom.rm.escape.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Rơm on 3/29/2018.
 */

public class UIButton {
    private Rect buttonRect; // biến lưu trữ kích thước button
    private Boolean buttonDown =false; // lưu trữ trạng thái
    private Bitmap buttonImage, buttonDownImage; // 2 hình ảnh nổi và chìm của button

    public UIButton(int left,int top, int right, int down, Bitmap buttonImage, Bitmap buttonDownImage) {
        this.buttonRect=new Rect(left,top,right,down);
        this.buttonImage = buttonImage;
        this.buttonDownImage = buttonDownImage;
    }
    public UIButton(int left, int top, int right, int bottom, Bitmap buttonImage) {
        buttonRect = new Rect(left, top, right, bottom);
        this.buttonImage = buttonImage;
    }


    //nếu đc nhấn xuống button sẽ chuyển sang giá trị true
    public void render(Painter g) {
           Bitmap currentButtonImage = buttonDown ? buttonDownImage : buttonImage;
           g.drawImage(currentButtonImage,buttonRect.left,buttonRect.top,buttonRect.width(),buttonRect.height());
           Log.d("render",buttonRect.left+" "+buttonRect.width());
    }
    public void onTouchDown(int touchX, int touchY) {
        if (buttonRect.contains(touchX, touchY)) {
            buttonDown = true;
        } else {
            buttonDown = false;
        }
    }
    public void cancel(){buttonDown=false;}
    //Nếu ng dùng vẫn giữ button thì botton sẽ chìm
    public  boolean isPressed(int touchX, int touchY){
        return buttonDown&&buttonRect.contains(touchX,touchY);

    }
    public boolean isButtonDown(){
        return buttonDown;
    }

}
