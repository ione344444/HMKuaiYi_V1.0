package com.xiaoxi.javademo.demos;

public class Demo42 {
    public static void main(String[] args) {
        int[][] sz = new int[2][3];
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 3; j ++){
                System.out.print("下标为(" + i + "," + j + ")的元素是：" + sz[i][j]);
            }
        }
    }
}
