package com.jancal.game.jan2048;

import com.jancal.game.jan2048.domain.Direction;

/**
 * @author ${user}
 * @date 一月 18 2017，10:59
 */
public interface IPlay {
    int[][] start();

    int[][] show();

    int[][] move(Direction direction);

    int getMax();

    int getStep();

    int getScore();
}