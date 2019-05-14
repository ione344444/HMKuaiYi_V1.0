package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo45 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入数组的行数：");
        int h = scanner.nextInt();
        System.out.print("\n请输入数组的列数：");
        int l = scanner.nextInt();

        int[][] ints = new int[h][l];

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = i + j;
            }
        }

        for (int i = ints.length - 1; i >= 0; i--) {
            for (int j = ints[i].length - 1; j >= 0; j--) {
                System.out.print(ints[i][j]+" ");
            }
            System.out.println();
        }
    }
}
