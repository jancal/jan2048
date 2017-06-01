package com.jancal.game.new2048;

import org.junit.Test;

/**
 * @author ${user}
 * @date 四月 21 2017，9:34
 */
public class BoardBaseRowColTest {

    @Test
    public void init() {
        Board boardBaseRowCol = new Board(4);
        System.out.println(boardBaseRowCol.toString());
    }

    @Test
    public void move() throws Exception {
        Board boardBaseRowCol = new Board(new int[][]{
                {0, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
        System.out.println(boardBaseRowCol.toString());
        boardBaseRowCol.move(Direction.left);
        System.out.println(boardBaseRowCol.toString());
    }



}