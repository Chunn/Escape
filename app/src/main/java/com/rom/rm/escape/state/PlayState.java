package com.rom.rm.escape.state;

import android.view.MotionEvent;

import com.rom.rm.escape.SkyWar.Assets;
import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.SkyWar.GameView;
import com.rom.rm.escape.model.Enemy;
import com.rom.rm.escape.model.Gift;
import com.rom.rm.escape.model.Player;
import com.rom.rm.escape.util.Painter;
import com.rom.rm.escape.util.UIButton;
import com.rom.rm.escape.util.UIText;

public class PlayState extends State {
    private static int score;
    private Player player;
    private Gift gift, gift1, gift2;
    private Enemy enemy, enemy1, enemy2;
    private UIButton btn_pause, btn_score;
    private UIText txtscore;
    private boolean isAlive;

    @Override
    public void init() {
        score = 0;
        isAlive = true;
        txtscore = new UIText(GameActivity.WIDTH - 200, 200, "0");
        player = new Player(0, GameActivity.HEIGHT - 100, 150, 150);
        gift = new Gift(GameActivity.WIDTH + 10, 0, 100, 100,(int) (GameActivity.HEIGHT*0.3/ GameView.FPS));
        gift1 = new Gift(GameActivity.WIDTH + 10, 10, 200, 200,(int) (GameActivity.HEIGHT*0.5/ GameView.FPS));
        gift2 = new Gift(GameActivity.WIDTH + 10, 50, 300, 300,(int) (GameActivity.HEIGHT*0.8/ GameView.FPS));
        enemy = new Enemy(GameActivity.WIDTH + 10, 20, 150, 150, Assets.planet,(int) (GameActivity.HEIGHT/ GameView.FPS));
        enemy1 = new Enemy(GameActivity.WIDTH + 10, 30, 250, 250, Assets.planet2,(int) (GameActivity.HEIGHT*0.2/ GameView.FPS));
        enemy2 = new Enemy(GameActivity.WIDTH + 10, 70, 350, 350, Assets.planet3,(int) (GameActivity.HEIGHT*0.5/ GameView.FPS));
        btn_pause = new UIButton(GameActivity.WIDTH - 100, 10, GameActivity.WIDTH - 100+88,10+88, Assets.btn_pause,Assets.btn_pause_down);
        btn_score = new UIButton(GameActivity.WIDTH - 300, 10, 600, 90, Assets.btn_score,Assets.btn_score_down);
        Assets.playSound(Assets.soundId);
    }

    @Override
    public void update(float delta) {
        player.update();
        gift.update(2);
        gift1.update(3);
        gift2.update(4);
        enemy.update(5);
        enemy1.update(6);
        enemy2.update(7);

        if (isAlive) {
            updateScore();
        }
        checkIsAlive();


    }

    private void checkIsAlive() {
        if (checkHitEnemy(enemy) || checkHitEnemy(enemy1) || checkHitEnemy(enemy2)) {
            isAlive = false;
            setCurrentState(new GameOverState(score));
            Assets.playSound(Assets.gameOverSound);
        }
    }

    private void updateScore() {
        setScore(gift, 5);
        setScore(gift1, 10);
        setScore(gift2, 15);
    }

    private boolean checkHitEnemy(Enemy enemy) {
        int x = (int) (enemy.getX() + enemy.getWidth() / 2);
        int y = (int) (enemy.getY() + enemy.getHeight() / 2);
        if (0 <= x && x <= player.getWidth() && player.getY() <= y && y <= player.getY()+player.getWidth()) {
            return true;
        } else return false;

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.menuBackground,0,0,GameActivity.WIDTH,GameActivity.HEIGHT);
        player.render(g);
        gift.render(g);
        gift1.render(g);
        gift2.render(g);
        enemy.render(g);
        enemy1.render(g);
        enemy2.render(g);
        btn_pause.render(g);
        btn_score.render(g);
        txtscore.render(g);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            player.onTouch(e, scaledX, scaledY);
            btn_pause.onTouchDown(scaledX, scaledY);
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            player.onTouch(e, scaledX, scaledY);
            if (btn_pause.isPressed(scaledX, scaledY)) {
                setPauseGame();
            }
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

    public static int getScore() {
        return score;
    }

    private void setScore(Gift gift, int score1) {
        int x = (int) (gift.getX() + gift.getWidth() / 2);
        int y = (int) (gift.getY() + gift.getHeight() / 2);
        if (0 <= x && x <= player.getWidth() && player.getY() <= y && y <= player.getY()+player.getWidth()) {
            score += score1;
            gift.setX(-GameActivity.WIDTH);
            txtscore.setText(score + "");
        }

    }
}
