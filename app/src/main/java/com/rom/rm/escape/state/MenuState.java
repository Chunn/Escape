package com.rom.rm.escape.state;

import android.util.Log;
import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.Assets;
import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.util.Painter;
import com.rom.rm.escape.util.UIButton;

/**
 * Created by Rơm on 3/29/2018.
 */

public class MenuState extends State {
    private UIButton playButton, scoreButton;

    @Override
    public void init() {
        playButton=new UIButton(700,330,1300,430, Assets.btn_play,Assets.btn_play_down);
        scoreButton=new UIButton(700,630,1300,700,Assets.btn_score_down,Assets.btn_score);
        Assets.playSound(Assets.soundId);
    }

    @Override
    public void update(float delta) {
       /* unload();
        setCurrentState(new PlayState());*/

    }

    @Override
    public void render(Painter painter) {
        painter.drawImage(Assets.menuBackground,0,0,GameActivity.WIDTH,GameActivity.HEIGHT);
        playButton.render(painter);
        scoreButton.render(painter);
        Log.d("render","render");
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction()== MotionEvent.ACTION_DOWN){
            playButton.onTouchDown(scaledX,scaledY);
            scoreButton.onTouchDown(scaledX,scaledY);
        }else if (e.getAction()==MotionEvent.ACTION_UP){
            if (scoreButton.isPressed(scaledX,scaledY)){
               GameActivity.showHighScore();
            }else if (playButton.isPressed(scaledX,scaledY));
            setCurrentState(new PlayState());
        }

        return true;
    }
    @Override
    public void load() {
        super.load();
    }

    @Override
    public void unload() {
        super.unload();
    }
}
