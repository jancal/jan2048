package com.jancal.game.jan2048.utils;

import java.util.Random;

/**
 * Utils
 *
 * @author Jancal
 * @date 2017/1/18
 */
public class Utils {

    private Utils() {
    }

    public static boolean isLog = true;

    public static int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static void sleep() {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void log(int[][] data) {
        if (isLog) {
            System.out.println(Matrix.matrix2Str(data));
        }
    }

    public static void log(String str) {
        if (isLog) {
            System.out.println(str);
        }
    }
}
