package com.jancal.game.new2048;

import com.jancal.game.jan2048.utils.FileUtils;
import com.sun.deploy.util.ArrayUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * PlayServiceImpl
 *
 * @author Jancal(Zhang Hao) 2017/4/20
 */
class PlayServiceImpl implements PlayService {
    private static final Logger logger = Logger.getLogger(PlayServiceImpl.class.getSimpleName());

    public void playByRandom() {
        AbstractBoard board = new Board(4);
        logger.info(board.toString());
        boolean gameIsOver = false;
        while (!gameIsOver) {
            Direction[] options = board.options();
            logger.info("options:" + Arrays.toString(options));
            Direction direction = options[new Random().nextInt(options.length)];
            logger.info("select:" + direction.name());
            board.move(direction);
            logger.info(board.toString());
            gameIsOver = board.gameIsOver();
        }
    }

    public void playByHuman() {
        AbstractBoard board = new Board(4);
        boolean gameIsOver = false;
        while (!gameIsOver) {
            logger.info(board.toString());
            Direction[] options = board.options();
            logger.info("options:" + options);
            //todo input from cmd
            Direction direction = options[0];
            logger.info("select:" + direction.name());
            board.move(direction);
            gameIsOver = board.gameIsOver();
        }
    }

    public void train() {
        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                logger.info(String.format("train the %s time", i));
            }
            AbstractBoard board = new Board(4);
            boolean gameIsOver = false;
            while (!gameIsOver) {
                Direction[] options = board.options();
                Direction direction = options[new Random().nextInt(options.length)];
                board.move(direction);
                gameIsOver = board.gameIsOver();
            }
            FileUtils.appendHistory(board.getHistory(), board.getMax());
        }
    }

    public void playByAi() {
        //todo
    }
}
