package com.rom.rm.escape.state;

import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.Assets;
import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.util.Painter;
import com.rom.rm.escape.util.UIButton;

public class PauseState extends State {
    UIButton btn_resume, btn_quit;
    @Override
    public void init() {
        btn_resume=new UIButton(700,330,1300,430, Assets.btn_play,Assets.btn_play_down);
        btn_quit=new UIButton(700,630,1300,700,Assets.btn_quit,Assets.btn_quit_down);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.menuBackground, GameActivity.WIDTH,GameActivity.HEIGHT);
        btn_resume.render(g);
        btn_quit.render(g);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            btn_resume.onTouchDown(scaledX, scaledY);
            btn_quit.onTouchDown(scaledX, scaledY);
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            if(btn_resume.isPressed(scaledX,scaledY)){
                setResumeGame();
            }
            if(btn_quit.isPressed(scaledX,scaledY)){
                GameActivity.setHighScore(PlayState.getScore());
                setCurrentState(new MenuState());
            }
        }
        return true;
    }

    @Override
    public void load() {
        super.load();
    }
}
