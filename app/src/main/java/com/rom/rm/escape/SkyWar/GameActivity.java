package com.rom.rm.escape.SkyWar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class GameActivity extends Activity {
    public  static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static GameView gameView;
    public static AssetManager assets;
    private static SharedPreferences prefs;
    private static final String highScoreKey = "highScoreKey";
    private static int highScore;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getPreferences(Activity.MODE_PRIVATE);
        highScore = retrieveHighScore();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        assets = getAssets();
        context=getApplicationContext();
        gameView = new GameView(this,WIDTH,HEIGHT);
        setContentView(gameView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
    }

    @Override
    protected  void onPause() {
        super.onPause();
        gameView.onPause(); //quit game
    }

    public static void setHighScore(int highScore) {
        GameActivity.highScore = highScore;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(highScoreKey, highScore);
        editor.commit();
    }

    private int retrieveHighScore() {

        return prefs.getInt(highScoreKey, 0);
    }

    public static int getHighScore() {

        return highScore;
    }
    public static void showHighScore(){
        Toast.makeText(context,getHighScore()+"",Toast.LENGTH_SHORT).show();
    }
}
