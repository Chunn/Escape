package com.rom.rm.escape.animation;

import com.rom.rm.escape.util.Painter;


/**
 * Created by Rơm on 3/29/2018.
 */

public class Animation {
    private Frame[] frames;
    private double[] frameEndTimes;
    private int currentFrameIndex = 0;
    private double currentTime = 0;
    private double totalDuration = 0;

    private boolean looping = true;

    //có thể lấy nhiều frame và các frame sẽ được hiển thị theo thứ tự đc thêm vào
    public Animation(boolean looping, Frame... frames) {
        this.frames = frames;
        this.looping = looping;
        frameEndTimes = new double[frames.length];
        Frame frame;

        for (int i = 0; i < frames.length; i++) {
            frame = frames[i];
            totalDuration += frame.getDuration();
            frameEndTimes[i] = totalDuration;
        }
    }

    //xử lý đa luồng
    public synchronized void update(float increment) {
        currentTime += increment;
        if ((currentTime > totalDuration) && looping) {
            restartAnimation();
        }
        //chưa hiển thị hết frame trong ds
        if (currentFrameIndex < frameEndTimes.length) {
            while (currentTime > frameEndTimes[currentFrameIndex]) {
                currentFrameIndex++;
            }
        }
    }

    private synchronized void restartAnimation() {
        currentFrameIndex = 0;
        currentTime %= totalDuration;
    }

    public synchronized void render(Painter painter, int x, int y) {
        painter.drawImage(frames[currentFrameIndex].getBitmap(), x, y);
    }

    public synchronized void render(Painter painter, int x, int y, int width, int height) {
        painter.drawImage(frames[currentFrameIndex].getBitmap(), x, y, width, height);
    }
}
