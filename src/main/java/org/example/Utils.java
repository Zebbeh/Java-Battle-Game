package org.example;

import java.io.Serializable;
import java.util.Random;

public class Utils implements Serializable {
    private static Random random = new Random();

    public static int randomNum(){
        int randomNum = random.nextInt(21-1)+1;
        return randomNum;
    }

    public static int randomHp(){
        int maxHp = 100;
        int minHp = 20;
        int randomHp = random.nextInt(maxHp-minHp) + minHp;
        return randomHp;
    }
}
