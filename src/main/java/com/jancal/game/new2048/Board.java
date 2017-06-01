package com.jancal.game.new2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import static javax.swing.UIManager.get;

/**
 * BoardBaseRowCol
 *
 * @author Jancal(Zhang Hao) 2017/4/20
 */
class Board extends AbstractBoard {
    private static final Logger logger = Logger.getLogger(Board.class.getSimpleName());

    private int size = 4;
    private Map<Coordinate, Block> blockMap = new HashMap<Coordinate, Block>();
    private Block[][] rows;//left
    private Block[][] cols;//up
    private Block[][] rowsReverse;//right
    private Block[][] colsReverse;//down

    private boolean gameIsOver = false;
    private int max = 0;
    private int score = 0;
    private int step = 0;
    private int sum = 0;
    private List<int[]> history = new ArrayList<int[]>();

    Board(int[][] data) {
        this.size = data.length;
        rows = new Block[size][size];
        cols = new Block[size][size];
        rowsReverse = new Block[size][size];
        colsReverse = new Block[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Block block = new Block(i, j, data[i][j]);
                blockMap.put(new Coordinate(i, j), block);
                rows[i][j] = block;
                cols[j][i] = block;
                rowsReverse[i][size - j - 1] = block;
                colsReverse[j][size - i - 1] = block;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Block current = blockMap.get(new Coordinate(i, j));
                Block left = blockMap.get(new Coordinate(i, j - 1));
                Block up = blockMap.get(new Coordinate(i - 1, j));
                Block right = blockMap.get(new Coordinate(i, j + 1));
                Block down = blockMap.get(new Coordinate(i + 1, j));
                current.left = left;
                current.up = up;
                current.right = right;
                current.down = down;
            }
        }
    }

    Board(int size) {
        this(new int[size][size]);

        //random
        int[] initValues = {2, 4};
        int initValue1 = initValues[new Random().nextInt(initValues.length)];
        int initValue2 = initValues[new Random().nextInt(initValues.length)];
        int position1 = new Random().nextInt(size * size);
        blockMap.get(new Coordinate(position1 / size, position1 % size)).value = initValue1;

        List<Integer> zeroBlocks = new ArrayList<Integer>(size * size - 1);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blockMap.get(new Coordinate(i, j)).value == 0) {
                    zeroBlocks.add(i * size + j);
                }
            }
        }
        int position2 = zeroBlocks.get(new Random().nextInt(zeroBlocks.size()));
        blockMap.get(new Coordinate(position2 / size, position2 % size)).value = initValue2;

        //sum and max
        max = initValue1 > initValue2 ? initValue1 : initValue2;
        sum = initValue1 + initValue2;
    }


    @Override
    int[] values() {
        int[] values = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                values[i * size + j] = blockMap.get(new Coordinate(i, j)).value;
            }
        }
        return values;
    }

    @Override
    boolean gameIsOver() {
        return gameIsOver;
    }

    @Override
    Direction[] options() {
        Set<Direction> options = new HashSet<Direction>(4);
        for (int i = 0; i < size; i++) {
            if (!options.contains(Direction.left)) {
                Block laseOfCurrentRow = blockMap.get(new Coordinate(i, size - 1));
                if (laseOfCurrentRow.moveIsAvailable(Direction.left)) {
                    options.add(Direction.left);
                }
            }
            if (!options.contains(Direction.right)) {
                Block firstOfCurrentRow = blockMap.get(new Coordinate(i, 0));
                if (firstOfCurrentRow.moveIsAvailable(Direction.right)) {
                    options.add(Direction.right);
                }
            }
            if (!options.contains(Direction.up)) {
                Block lastOfCurrentCol = blockMap.get(new Coordinate(size - 1, size - i - 1));
                if (lastOfCurrentCol.moveIsAvailable(Direction.up)) {
                    options.add(Direction.up);
                }
            }
            if (!options.contains(Direction.down)) {
                Block firstOfCurrentCol = blockMap.get(new Coordinate(0, size - i - 1));
                if (firstOfCurrentCol.moveIsAvailable(Direction.down)) {
                    options.add(Direction.down);
                }
            }
        }
        assert options.size() <= 4;
        return options.toArray(new Direction[options.size()]);
    }

    @Override
    int[] testMove(Direction direction) {
        try {
            Board test = (Board) this.clone();
            test.move(direction);
            return test.values();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    private void removeZero(Block[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].value == 0) {
                for (int j = i + 1; j < row.length; j++) {
                    if (row[j].value > 0) {
                        row[i].value = row[j].value;
                        row[j].value = 0;
                        break;
                    }
                }
            }
        }
    }

    private void merge(Block[] row) {
        for (int i = 0; i < size - 1; i++) {
            if (row[i].value > 0 && row[i].value == row[i + 1].value) {
                row[i].value *= 2;
                row[i + 1].value = 0;
                score++;//score
            }
        }
    }

    void move(Direction direction) {
        if (gameIsOver) throw new IllegalStateException("Game Over");
        //计步
        step++;

        //move
        switch (direction) {
            case left:
                for (int i = 0; i < size; i++) {
                    removeZero(rows[i]);
                    merge(rows[i]);
                    removeZero(rows[i]);
                }
                break;
            case up:
                for (int i = 0; i < size; i++) {
                    removeZero(cols[i]);
                    merge(cols[i]);
                    removeZero(cols[i]);
                }
                break;
            case right:
                for (int i = 0; i < size; i++) {
                    removeZero(rowsReverse[i]);
                    merge(rowsReverse[i]);
                    removeZero(rowsReverse[i]);
                }
                break;
            case down:
                for (int i = 0; i < size; i++) {
                    removeZero(colsReverse[i]);
                    merge(colsReverse[i]);
                    removeZero(colsReverse[i]);
                }
                break;
            default:
                throw new IllegalArgumentException("unknown direction");
        }

        //is it over?
        List<Integer> zeroBlocks = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Block block = blockMap.get(new Coordinate(i, j));
                if (block.value == 0) {
                    zeroBlocks.add(i * size + j);//空白的块
                }
            }
        }
        if (zeroBlocks.size() == 0) {
            gameIsOver = true;//game is over
            return;
        }

        //random
        int position = zeroBlocks.get(new Random().nextInt(zeroBlocks.size()));
        int x = position / size;
        int y = position % size;
        int[] newValues = {2, 4};
        blockMap.get(new Coordinate(x, y)).value = newValues[new Random().nextInt(2)];

        //sum、max、history and zeroBlocks
        sum = 0;
        int[] currentValues = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Block block = blockMap.get(new Coordinate(i, j));
                sum += block.value;//总和
                max = block.value > max ? block.value : max;//最大值
                currentValues[i * size + j] = block.value;//历史记录
            }
        }
        history.add(currentValues);

        if (options().length == 0) {
            gameIsOver = true;//game is over
        }
    }

    @Override
    int getMax() {
        return max;
    }

    @Override
    int getScore() {
        return score;
    }

    @Override
    List<int[]> getHistory() {
        return history;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("step:").append(step).append(",score:").append(score)
                .append(",max:").append(max).append(",sum:").append(sum);
        for (int i = 0; i < size; i++) {
            sb.append("\n");
            for (int j = 0; j < size; j++) {
                Block block = blockMap.get(new Coordinate(i, j));
                sb.append(block.value).append("\t\t");
            }
        }
        return sb.toString();
    }
}
