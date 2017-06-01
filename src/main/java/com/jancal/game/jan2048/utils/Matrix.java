package com.jancal.game.jan2048.utils;

/**
 * Matrix
 *
 * @author Jancal
 * @date 2017/1/18
 */
public class Matrix {
    public static int[][] initMatrix() {
        return new int[][]{
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 0, 0}};
    }

    /**
     * 计算可以合并的对数
     *
     * @param data0
     * @return
     */
    public static int countNearSame(int[][] data0) {
        int[][] data = clone(data0);
        int res = 0;
        for (int[] row : data) {
            res += countNearSame(row);
        }
        rotate(data);
        for (int[] row : data) {
            res += countNearSame(row);
        }
        return res;
    }

    public static int countNearSame(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0) {
                for (int j = i + 1; j < row.length; j++) {
                    if (row[j] != 0) {
                        if (row[i] == row[j]) {
                            count++;
                        }
                        break;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Assert.isTrue(countNearSame(new int[][]{
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 0, 0},
                new int[]{1, 0, 1, 0}}) == 1);
    }

    public static int[] matrix2array(int[][] data) {
        int[] res = new int[data.length * data.length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                res[i * data.length + j] = data[i][j];
            }
        }
        return res;
    }

    public static int[][] array2matrix(int[] array, int size) {
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i][j] = array[i * size + j];
            }
        }
        return res;
    }

    public static void copy(int[] res, int[] row) {
        for (int i = 0; i < res.length; i++) {
            row[i] = res[i];
        }
    }

    public static void copy(int[][] data1, int[][] data2) {
        for (int i = 0; i < data1.length; i++) {
            for (int j = 0; j < data1.length; j++) {
                data2[i][j] = data1[i][j];
            }
        }
    }

    public static boolean isChanged(int[][] oldData, int[][] data) {
        return !isEqual(oldData, data);
    }

    public static boolean isEqual(int[][] oldData, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (oldData[i][j] != data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void rotate(int[][] data) {
        int[][] tmp = new int[data.length][data.length];
        copy(data, tmp);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = tmp[j][i];
            }
        }
    }

    public static void reverse(int[] row) {
        for (int i = 0; i < row.length / 2; i++) {
            int tmp = row[i];
            row[i] = row[row.length - i - 1];
            row[row.length - i - 1] = tmp;
        }
    }

    public static String matrix2Str(int[][] data) {
        String str = "\n";
        for (int[] ints : data) {
            for (int anInt : ints) {
                str += anInt == 0 ? "." : anInt;
                str += "\t";
            }
            str += "\n";
        }
        return str;
    }

    private static String fillBlank(int i) {
        if (i <= 0) {
            return "   0";
        } else if (i < 10) {
            return "   " + i;
        } else if (i < 100) {
            return "  " + i;
        } else if (i < 1000) {
            return " " + i;
        } else {
            return "" + i;
        }
    }

    public static String matrix2txt(int[][] data) {
        String str = "";
        for (int[] ints : data) {
            for (int anInt : ints) {
                str += anInt;
                str += "\t";
            }
        }
        return str;
    }

    public static int countZero(int[][] data) {
        int res = 0;
        for (int[] ints : data) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int getMax(int[][] data) {
        int max = -1;
        for (int[] ints : data) {
            for (int anInt : ints) {
                max = anInt > max ? anInt : max;
            }
        }
        return max;
    }

    public static int sum(int[][] data) {
        int sum = 0;
        for (int[] ints : data) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        return sum;
    }

    public static int[][] clone(int[][] data0) {
        int[][] data = new int[data0.length][data0.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = clone(data0[i]);
        }
        return data;
    }

    public static int[] clone(int[] row0) {
        int[] row = new int[row0.length];
        for (int i = 0; i < row0.length; i++) {
            row[i] = row0[i];
        }
        return row;
    }
}
