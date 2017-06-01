package com.jancal.game.jan2048;

import com.jancal.game.jan2048.domain.Direction;
import com.jancal.game.jan2048.utils.FileUtils;
import com.jancal.game.jan2048.utils.Matrix;
import com.jancal.game.jan2048.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Bootstrap
 *
 * @author Jancal
 * @date 2017/1/18
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            play();
        }

//        train();
    }

    public static void play() {
        auto(true);
    }

    public static void train() {
        for (int i = 0; i < 1000; i++) {
            auto(false);
            if (i % 100 == 0) {
                System.out.println("done:" + i);
            }
        }
    }

    public static void human() {
        IPlay game = new Game();
        System.out.println(game.toString());

        boolean flag = true;
        while (flag) {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String str;
            boolean isChanged;
            try {
                str = br.readLine();
                if (str.equals("a") || str.equals("4")) {
                    game.move(Direction.left);
                } else if (str.equals("d") || str.equals("6")) {
                    game.move(Direction.up);
                } else if (str.equals("w") || str.equals("8")) {
                    game.move(Direction.right);
                } else if (str.equals("s") || str.equals("2")) {
                    game.move(Direction.down);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }


    public static void auto(boolean isLog) {

        Utils.isLog = isLog;
        IPlay game = new Game();
        int[][] board = game.start();
//        Utils.log(game.show());

        int n = 1000;
        List<String> list = new ArrayList<String>();
        list.add(Matrix.matrix2txt(board));
        int max = 0;
        while (n > 0) {
            //产生合法走子
            int[][] testLeft = Game.test(board, Direction.left);
            int[][] testUp = Game.test(board, Direction.up);
            int[][] testRight = Game.test(board, Direction.right);
            int[][] testDown = Game.test(board, Direction.down);

            double evaluateLeft;
            double evaluateUp;
            double evaluateRight;
            double evaluateDown;
            if (!isLog) {
                //评估
                evaluateLeft = Evaluator.evaluate(testLeft);
                evaluateUp = Evaluator.evaluate(testUp);
                evaluateRight = Evaluator.evaluate(testRight);
                evaluateDown = Evaluator.evaluate(testDown);
            } else {
                evaluateLeft = Evaluator.evaluatePlay(testLeft);
                evaluateUp = Evaluator.evaluatePlay(testUp);
                evaluateRight = Evaluator.evaluatePlay(testRight);
                evaluateDown = Evaluator.evaluatePlay(testDown);
            }

            //选择
            Direction d = Evaluator.select(evaluateLeft, evaluateUp, evaluateRight, evaluateDown);
            if (d == null) {
                Utils.log("Game over (max:" + game.getMax() + ",step:" + game.getStep() + ",score:" + game.getScore() + ")");
                break;
            }
//            Utils.log(d.print());

            //执行
            board = game.move(d);
//            Utils.log("go on (max:" + game.getMax() + ",step:" + game.getStep() + ",score:" + game.getScore() + ")");
//            Utils.log(game.show());
            max = game.getMax();
            list.add(Matrix.matrix2txt(board));
            n--;
        }

        if (!isLog) {
            FileUtils.append(list, max);
        }
    }


}
