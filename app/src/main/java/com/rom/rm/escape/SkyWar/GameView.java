package com.rom.rm.escape.SkyWar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.rom.rm.escape.state.LoadState;
import com.rom.rm.escape.state.MenuState;
import com.rom.rm.escape.state.PauseState;
import com.rom.rm.escape.state.PlayState;
import com.rom.rm.escape.state.State;
import com.rom.rm.escape.util.InputHandle;
import com.rom.rm.escape.util.Painter;

/**
 * Created by Rơm on 3/29/2018.
 */

public class GameView extends SurfaceView implements Runnable{
    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Thread gameThread;
    private Painter graphics;

    private Thread GameThread;
    public static final int FPS=60;
    private double averageFPS;
    private volatile boolean running = false; //volatile=> keyWord xử lý đa luồng
    private volatile State currentState;
    private volatile State preState = null;

    private InputHandle inputHandler;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, int with, int height) {

        super(context);
        gameImage=Bitmap.createBitmap(with,height,Bitmap.Config.RGB_565);
        gameImageSrc= new Rect(0,0,gameImage.getWidth(),gameImage.getHeight());
        gameImageDst= new Rect();
        gameCanvas=new Canvas(gameImage);
        graphics=new Painter(gameCanvas);


        //surface holder dùng để thay đổi hình hạng kích thước của surface view Cung cấp giao diện callback
        SurfaceHolder holder = getHolder();
        //gắn listenner của giao diện callback và đối tượng surface holder
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initInput();
                if (currentState==null){
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                pause();

            }
        });
    }
    private void initInput() {
        if(inputHandler == null) {
            inputHandler = new InputHandle();
        }
        setOnTouchListener(inputHandler);
    }
    private void initGame(){
     running=true;
     gameThread=new Thread(this,"game thread");
     gameThread.start();
    }
    public void setPause() {
        PauseState pauseState = new PauseState();
        pauseState.init();
        preState = currentState;
        currentState = pauseState;
        inputHandler.setCurrentState(currentState);
    }

    public void setResume() {
        if(preState!=null){
            currentState = preState;
            inputHandler.setCurrentState(currentState);
        }
    }
    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState=newState;
        inputHandler.setCurrentState(currentState);
    }
    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        //Game loop
        while(running) {
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);

            try {
                Thread.sleep(sleepDurationMillis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void updateAndRender(long delta) {
        currentState.update(delta / 1000f);
        currentState.render(graphics);
        renderGameImage();
    }

    private void renderGameImage() {
        Canvas canvas=getHolder().lockCanvas(); //giấy=>vẽ, đến khí gọi unlockCanvasAndPost mới đc vẽ
        if (canvas!=null){
            canvas.getClipBounds(gameImageDst);//lấy 1 hình chữ nhật bao quanh vingf cắt của gameImageDSt
            canvas.drawBitmap(gameImage,gameImageSrc,gameImageDst,null);
            getHolder().unlockCanvasAndPost(canvas);

        }
    }

    public void onResume(){
       if (currentState!=null){
           currentState.onResume();
       }
    }

    public void onPause(){
        if (currentState!=null){
            currentState.onPause();
        }
    }
    private void pause(){
        running=false;
        while (gameThread.isAlive()){
            try {
                gameThread.join(); //join thread hiện tại vào cho đến khi thread hiện tại chết
            } catch (InterruptedException e) {
                e.printStackTrace();// ném exception khi thread bị gián đoạn
            }
        }
    }

}
