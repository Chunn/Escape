package com.rom.rm.escape.util;

import android.view.MotionEvent;
import android.view.View;

import com.rom.rm.escape.SkyWar.GameActivity;
import com.rom.rm.escape.state.State;

/**
 * Created by Rơm on 3/29/2018.
 */
//xử lý tương tác (nhận xử lý đầu vào)// lắng nghe sự kiện onTouch
public class InputHandle implements View.OnTouchListener{
    private State currentState;
//tại 1 thời điểm chỉ có 1 state chạy chính dùng tham chiếu currentSate
//  (trong mỗi state tương ứng sẽ xử lý sự kiện onTouch)
    public void setCurrentState (State currentState) {
        this.currentState = currentState;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //tọa độ trả về là của service view -> vẽ trên bit map nên cần chuyển về tọa độ của bit map
        int scaleX=(int)(motionEvent.getX()/view.getWidth())* GameActivity.WIDTH;//hoành độ của bitmap-> chuyển sang hoành độ của service view
        int scaleY=(int)(motionEvent.getY()/view.getHeight())*GameActivity.HEIGHT;
        return currentState.onTouch(motionEvent,scaleX,scaleY);
    }
}
