package com.jancal.game.jan2048;

import com.jancal.game.jan2048.domain.Direction;
import com.jancal.game.jan2048.utils.Matrix;

import java.util.Random;

import static com.jancal.game.jan2048.utils.Matrix.*;
import static com.jancal.game.jan2048.utils.Utils.generateRandom;


/**
 * Game
 *
 * @author Jancal
 * @date 2017/1/17
 */
public class Game implements IPlay {


    private int[][] data = Matrix.initMatrix();
    private int[][] oldData = Matrix.initMatrix();
    private static int size = 4;
    private static int step = 0;
    private static int score = 0;

    public int[][] start() {
        data = add(data);
        data = add(data);
        return show();
    }

    public int[][] show() {
        int[][] res = new int[size][size];
        copy(data, res);
        return res;
    }

    public static int[][] test(int[][] data0, Direction direction) {
        int[][] data = new int[size][size];
        copy(data0, data);

        if (direction.equals(Direction.left)) {
            for (int[] row : data) {
                moveRowLeft(row);
            }
        } else if (direction.equals(Direction.up)) {
            rotate(data);
            for (int[] row : data) {
                moveRowLeft(row);
            }
            rotate(data);
        } else if (direction.equals(Direction.right)) {
            for (int[] row : data) {
                reverse(row);
                moveRowLeft(row);
                reverse(row);
            }
        } else if (direction.equals(Direction.down)) {
            rotate(data);
            for (int[] row : data) {
                reverse(row);
                moveRowLeft(row);
                reverse(row);
            }
            rotate(data);
        } else {
            throw new RuntimeException("unknown direction");
        }

        if (isChanged(data0, data)) {
            return data;
        } else {
            return null;
        }
    }


    public int[][] move(Direction direction) {
        boolean isChanged;
        if (direction.equals(Direction.left)) {
            isChanged = moveLeft();
        } else if (direction.equals(Direction.up)) {
            isChanged = moveUp();
        } else if (direction.equals(Direction.right)) {
            isChanged = moveRight();
        } else if (direction.equals(Direction.down)) {
            isChanged = moveDown();
        } else {
            throw new RuntimeException("unknown direction");
        }
        if (isChanged) {
            step++;
            data = add(data);
        }
        return show();
    }

    public int getMax() {
        return Matrix.getMax(data);
    }

    public int getStep() {
        return step;
    }

    public int getScore() {
        return score;
    }


    public boolean moveLeft() {
        copy(data, oldData);

        for (int[] row : data) {
            score += moveRowLeft(row);
        }
        return isChanged(oldData, data);
    }


    public boolean moveRight() {
        copy(data, oldData);
        for (int[] row : data) {
            reverse(row);
            score += moveRowLeft(row);
            reverse(row);
        }
        return isChanged(oldData, data);
    }

    public boolean moveUp() {
        copy(data, oldData);
        rotate(data);
        for (int[] row : data) {
            score += moveRowLeft(row);
        }
        rotate(data);
        return isChanged(oldData, data);
    }

    public boolean moveDown() {
        copy(data, oldData);
        rotate(data);
        for (int[] row : data) {
            reverse(row);
            score += moveRowLeft(row);
            reverse(row);
        }
        rotate(data);
        return isChanged(oldData, data);
    }

    private static int[][] add(int[][] data) {
        int[] array = matrix2array(data);

        int[] positions = new int[array.length];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                positions[index] = i;
                index++;
            }
        }

        int max = index;
        int min = 0;
        int r = generateRandom(min, max);
        array[positions[r]] = addVal();


        return array2matrix(array, size);

    }

    private static int addVal() {
        int[] vals = {2, 2, 2, 4};
        int max = 4;
        int min = 0;
        Random random = new Random();
        int r = random.nextInt(max) % (max - min + 1) + min;
        return vals[r];
    }


    private static int moveRowLeft(int[] row) {
        //remove zero
        removeRowZero(row);
        //merge
        int res = merge(row);
        //remove zero again
        removeRowZero(row);

        return res;
    }

    private static int merge(int[] row) {
        int res = 0;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0 && row[i] == row[i + 1]) {
                res += row[i];//被合并的值
                row[i] *= 2;
                row[i + 1] = 0;
            }
        }
        return res;
    }

    private static void removeRowZero(int[] row) {
        int[] res = new int[row.length];
        int index = 0;
        //copy to res
        for (int aRow : row) {
            if (aRow > 0) {
                res[index] = aRow;
                index++;
            }
        }
        //copy to row
        copy(res, row);
    }


}
