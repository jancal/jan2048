package com.jancal.game.new2048;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ${user}
 * @date 四月 27 2017，15:01
 */
public class BoardTest {
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board(new int[][]{
                {8, 8, 8, 8},
                {0, 8, 0, 8},
                {0, 0, 8, 8},
                {0, 8, 0, 8}});
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void move() throws Exception {
        System.out.println(board);
        board.move(Direction.left);
        System.out.println(board);
    }

}