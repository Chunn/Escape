package com.rom.rm.escape.state;

import android.app.Notification;
import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.Assets;
import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.util.Painter;
import com.rom.rm.escape.util.UIButton;
import com.rom.rm.escape.util.UIText;

public class GameOverState extends State
{
    private int playerScore;
    private UIButton btn_restart, btn_quit;
    private UIText score;

    public GameOverState(int playerScore) {
        this.playerScore = playerScore;
    }

    @Override
    public void init() {
        Assets.playSound(Assets.gameOverSound);
        score=new UIText(1000,400,playerScore+"");
        btn_restart=new UIButton(900,230,1200,330, Assets.btn_restart,Assets.btn_restart_down);
        btn_quit=new UIButton(900,430,1200,500,Assets.btn_quit,Assets.btn_quit_down);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.menuBackground,0,0,GameActivity.WIDTH,GameActivity.HEIGHT);
        g.drawImage(Assets.gameOverBG,750,450,700,500);

        score.render(g);
        btn_restart.render(g);
        btn_quit.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction()== MotionEvent.ACTION_DOWN){
            btn_restart.onTouchDown(scaledX,scaledY);
            btn_quit.onTouchDown(scaledX,scaledY);
        }else if (e.getAction()==MotionEvent.ACTION_UP){
            if (btn_restart.isPressed(scaledX,scaledY)){
                GameActivity.setHighScore(playerScore);
                setCurrentState(new PlayState());
            }else if (btn_quit.isPressed(scaledX,scaledY));
            GameActivity.setHighScore(playerScore);
            setCurrentState(new MenuState());
        }

        return true;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void load() {
        super.load();
    }
}
