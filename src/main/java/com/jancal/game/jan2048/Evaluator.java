package com.jancal.game.jan2048;

import com.jancal.game.jan2048.domain.Direction;
import com.jancal.game.jan2048.utils.Matrix;

/**
 * Evaluator
 *
 * @author Jancal
 * @date 2017/1/18
 */
public class Evaluator {

    /**
     * 评估
     */
    public static double evaluate(int[][] data) {
        double res = 0;
        if (data == null) {
            return -1000;
        }
//        res = (Matrix.countZero(data) + Matrix.getMax(data) + Matrix.countNearSame(data)) / (float) Matrix.sum(data);

        //模型1
//        double[] coef = new double[]{
//                0.41926821,  0.47877594,  0.49812224,  0.48400885,
//                0.45204841,  0.4673591 ,  0.4669733 ,  0.50301929,
//                0.34839361,  0.49623837,  0.47239426,  0.49164798,
//                0.49720204,  0.479389  ,  0.51692508,  0.48400889};

        //模型2
//        double[] coef = new double[]{
//                0.04467248, 0.20835407, 0.21232355, 0.03849335,
//                0.20035299,0.25378068, 0.26575652, 0.19768645,
//                0.20369744, 0.25825037,0.25412486, 0.19729681,
//                0.02099921, 0.18905951, 0.21652246,0.03246331};
        //模型3
        double[] coef = new double[]{
                0.03106106, 0.18708798, 0.19578812, 0.01911119,
                0.20263048, 0.25546184, 0.25730161, 0.19394336,
                0.19225672, 0.25507562, 0.25865609, 0.20195716,
                0.02044561, 0.18859891, 0.1938103, 0.0097532};

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                res += coef[i * data.length + j] * data[i][j];
            }
        }

        return res;
    }

    public static double evaluatePlay(int[][] data) {
        if (data == null) {
            return -1000;
        }
        return (Matrix.countZero(data) + Matrix.getMax(data) + Matrix.countNearSame(data)) / (float) Matrix.sum(data);
    }

    public static void main(String[] args) {
        float a = 10;
        float b = 3;
        System.out.println(a / b);
    }

    public static Direction select(double left, double up, double right, double down) {
        double max = -1;
        max = left > max ? left : max;
        max = up > max ? up : max;
        max = right > max ? right : max;
        max = down > max ? down : max;

        if (max < 0) {
            return null;
        }

        if (max == down) {
            return Direction.down;
        } else if (max == left) {
            return Direction.left;
        } else if (max == right) {
            return Direction.right;
        } else if (max == up) {
            return Direction.up;
        } else {
            throw new RuntimeException("unknown error");
        }
    }
}
