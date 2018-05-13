package com.rom.rm.escape.state;

import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.util.Painter;

/**
 * Created by Rơm on 3/29/2018.
 */

public abstract class State {
    public void setCurrentState(State newState){

        GameActivity.gameView.setCurrentState(newState);
    }
    public void setPauseGame() {
        GameActivity.gameView.setPause();
    }

    public void setResumeGame(){
        GameActivity.gameView.setResume();
    }
    public abstract void init();
    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public void onPause(){}

    public void onResume() {}

    public void load() {}

    public void unload() {}

}
