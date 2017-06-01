package com.jancal.game.jan2048.domain;

/**
 * @author ${user}
 * @date 一月 18 2017，11:01
 */
public enum Direction {
    left(0), up(1), right(2), down(3);

    private int index;

    Direction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String print() {
        String str;
        switch (index) {
            case 0:
                str = "←";
                break;
            case 1:
                str = "↑";
                break;
            case 2:
                str = "→";
                break;
            case 3:
                str = "↓";
                break;
            default:
                str = null;
                break;
        }
        return str;
    }
}
