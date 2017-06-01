package com.jancal.game.jan2048.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * FileUtils
 *
 * @author Jancal
 * @date 2017/1/18
 */
public class FileUtils {
    public static void main(String[] args) {
        append("book.txt", "a\n");
    }

    public static void append(List<String> list, int max) {
        for (String s : list) {
            append("data6.txt", s + max + "\n");
        }
    }

    public static void appendHistory(List<int[]> list, int max) {
        for (int[] history : list) {
            String str = "";
            for (int i = 0; i < history.length; i++) {
                str += history[i] + ",";
            }
            append("data6.txt", str + max + "\n");
        }
    }

    private static void append(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
