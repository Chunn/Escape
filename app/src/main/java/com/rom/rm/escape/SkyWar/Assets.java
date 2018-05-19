package com.rom.rm.escape.SkyWar;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.PictureDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.graphics.BitmapFactory.Options;
import android.os.Build;

import com.rom.rm.escape.animation.Animation;
import com.rom.rm.escape.animation.Frame;

import java.io.IOException;
import java.io.InputStream;

public class Assets {
    private static MediaPlayer mediaPlayer;

    private static SoundPool soundPool;

    public static Bitmap menuBackground,gameOverBG,winBg;

    public static Bitmap enemy,planet,planet2,planet3,player;

    public static Bitmap btn_play,btn_score, btn_play_down,btn_score_down, btn_resume,btn_resume_down,
    btn_pause,btn_pause_down,btn_restart,btn_restart_down,btn_quit,btn_quit_down;

    public static Animation run;

    public static Typeface typeface;

    public static int soundId=-1, gameOverSound=-1;

    public static void load(){
        menuBackground=loadBitmap("galaxy.jpg",true);
        gameOverBG=loadBitmap("game_over.png",true);
        winBg=loadBitmap("win.png",true);

        btn_play=loadBitmap("btn_play.png",true);
        btn_play_down=loadBitmap("play_full_color.png",true);
        btn_score=loadBitmap("score.png",true);
        btn_score_down=loadBitmap("score_do.png",true);
        btn_restart=loadBitmap("restart.png",true);
        btn_restart_down=loadBitmap("restart_down.png",true);
        btn_quit=loadBitmap("quit.png",true);
        btn_quit_down=loadBitmap("quit_down.png",true);
        btn_pause=loadBitmap("pause.png",true);
        btn_pause_down=loadBitmap("pause_do.png",true);



        player=loadBitmap("player.png",true);
        enemy=loadBitmap("enemy.png",true);
        planet=loadBitmap("planet.png",true);
        planet2=loadBitmap("planet2.png",true);
        planet3=loadBitmap("planet3.png",true);


        soundPool=buildSoundPool();
        soundId=loadSound("theme.mp3");
        gameOverSound=loadSound("lose.mp3");

        typeface=Typeface.create(Typeface.createFromAsset(GameActivity.assets,"UVNBanhMi.TTF"),Typeface.BOLD);
        run=new Animation(true,new Frame(menuBackground,GameView.FPS), new Frame(menuBackground,GameView.FPS));


    }
    public static Bitmap loadBitmap(String fileName, boolean transparency){
        InputStream inputStream=null;
        try {
           inputStream=GameActivity.assets.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options=new Options();
        if (transparency){
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }else {
            options.inPreferredConfig=Bitmap.Config.RGB_565;
        }
        Bitmap bitmap= BitmapFactory.decodeStream(inputStream,null,options);
        return bitmap;
    }
    public static void unloadBitmap(Bitmap bitmap){
        bitmap.recycle(); //clean up memory
        bitmap=null; //unload bitmap
    }
    public static int loadSound(String fileName){
        int soundId=0;
        if (soundPool==null){
             buildSoundPool();
        }
        try {
            soundId=soundPool.load(GameActivity.assets.openFd(fileName),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundId;

    }
    public static SoundPool buildSoundPool(){
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(25)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else {
            soundPool=new SoundPool(25, AudioManager.STREAM_MUSIC,0);
        }
        return soundPool;
    }
    public static Bitmap convertPictureDrawableToBitmap(PictureDrawable pictureDrawable){
        Bitmap bitmap = Bitmap.createBitmap(pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawPicture(pictureDrawable.getPicture());
        return bitmap;
    }
    //Phát âm thanh đã được tải vào bộ nhớ
    public static void playSound(int soundID) {
        if (soundPool != null) {
            soundPool.play(soundID, 1, 1, 1, 0, 1);
        }
    }

    //phát stream music (k cần tải vào bộ nhớ)
    public static void playMusic(String fileName, boolean looping) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            AssetFileDescriptor afd = GameActivity.assets.openFd(fileName);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(looping);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void pause(){
        if (soundPool!=null){
            soundPool.release();//giải phóng
            soundPool=null;
        }
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
    public static void onResume() {

    }
}
