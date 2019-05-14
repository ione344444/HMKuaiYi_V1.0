package com.xiaoxi.javademo.demos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Demo46 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入数组行数：");
        int h = scanner.nextInt();
        System.out.print("\n请输入数组列数：");
        int l = scanner.nextInt();

        int[][] ints = new int[h][l];

        Random random = new Random();
        for (int i = 0;i < ints.length;i++){
            for (int j = 0;j < ints[i].length;j++){
                ints[i][j] = random.nextInt(21)-10;
            }
        }

        int min = ints[0][0];
        for (int i = 0;i < ints.length;i++){
            for (int j = 0;j < ints[i].length;j++){
                if (ints[i][j] < min){
                    min = ints[i][j];
                }
            }
        }
        System.out.println("最小值：" + min);
    }
}
