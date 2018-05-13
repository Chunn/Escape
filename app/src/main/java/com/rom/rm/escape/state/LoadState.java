package com.rom.rm.escape.state;

import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.Assets;
import com.rom.rm.escape.util.Painter;

public class LoadState extends State {
    @Override
    public void init() {
        load();
        setCurrentState(new MenuState());
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());


    }

    @Override
    public void render(Painter g) {

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
        Assets.load();
    }

    @Override
    public void unload() {
    }
}
