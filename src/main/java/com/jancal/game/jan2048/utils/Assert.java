package com.jancal.game.jan2048.utils;

/**
 * Assert
 *
 * @author Jancal
 * @date 2017/1/19
 */
public class Assert {
    public static void isTrue(boolean express) {
        if (express) {
            System.out.println("-------success---------");
        } else {
            System.out.println("=========fail=========");
        }

    }
}
