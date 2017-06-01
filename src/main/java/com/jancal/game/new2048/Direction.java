package com.jancal.game.new2048;

/**
 * @author ${user}
 * @date 一月 18 2017，11:01
 */
public enum Direction {
    left, up, right, down;

    @Override
    public String toString() {
        return this.name();
    }
}
