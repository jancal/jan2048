package com.jancal.game.new2048;

import java.util.logging.Logger;

/**
 * Player
 *
 * @author Jancal(Zhang Hao) 2017/4/20
 */
class Player {
    private static final Logger logger = Logger.getLogger(Player.class.getSimpleName());
    private PlayService playService;

    Player(PlayService playService) {
        this.playService = playService;
    }

    void start() {
        playService.playByRandom();
    }
}
