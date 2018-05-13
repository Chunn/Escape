package com.rom.rm.escape.util;

import java.util.Random;

/**
 * Created by Rơm on 3/29/2018.
 */

public class RandomNumberGenerator {
    private static Random random=new Random();
    public static int getRandInBetween(int min,int max){
        return random.nextInt(max-min)+min; //sinh số ngẫu nhiên từ khoảng min->max
    }
    public static int getRandIn(int max){

        return random.nextInt(max); //sinh số ngẫu nhiên từ 0->max-1
    }

}
