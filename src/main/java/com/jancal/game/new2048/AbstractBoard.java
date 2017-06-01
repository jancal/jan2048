package com.jancal.game.new2048;

import java.util.List;

/**
 * AbstractBoard
 *
 * @author Jancal(Zhang Hao) 2017/4/20
 */
abstract class AbstractBoard {

    abstract int[] values();

    /**
     * 是否已结束
     *
     * @return 是否已结束
     */
    abstract boolean gameIsOver();

    /**
     * 可选操作
     *
     * @return 可选操作
     */
    abstract Direction[] options();

    /**
     * 测试操作
     *
     * @param direction 方向
     * @return 对应的结果
     */
    abstract int[] testMove(Direction direction);

    /**
     * 走子
     *
     * @param direction 方向
     * @return 操作是否生效
     */
    abstract void move(Direction direction);


    /**
     * 历史记录
     *
     * @return 历史记录
     */
    abstract List<int[]> getHistory();

    /**
     * 获取分数
     *
     * @return 分数
     */
    abstract int getScore();

    /**
     * 获取最大值
     *
     * @return 最大值
     */
    abstract int getMax();


}
