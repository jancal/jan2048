package com.jancal.game.new2048;

/**
 * Coordinate
 *
 * @author Jancal(Zhang Hao) 2017/4/26
 */
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate other = (Coordinate) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(x).hashCode() + "," + String.valueOf(y).hashCode()).hashCode();
    }
}
