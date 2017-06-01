package com.jancal.game.new2048;

/**
 * Player
 *
 * @author Jancal(Zhang Hao) 2017/4/20
 */
public class PlayerBuilder {
    private Player player;

    public static void main(String[] args) {
        new PlayerBuilder().build().start();
    }

    public PlayerBuilder build() {
        PlayService playService = new PlayServiceImpl();
        player = new Player(playService);
        return this;
    }


    public PlayerBuilder start() {
        player.start();
        return this;
    }
}
