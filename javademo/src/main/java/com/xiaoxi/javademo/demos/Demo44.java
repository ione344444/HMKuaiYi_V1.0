package com.xiaoxi.javademo.demos;

public class Demo44 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{
                {1,2,3},
                {3,2,1},
                {0,0,0}
        };

        for (int i = 0;i < ints.length;i++){
            for (int j = 0;j < ints[i].length;j++){
                if (ints[i][j] % 2 == 0){
                    ints[i][j] = 0;
                }else {
                    ints[i][j] = 1;
                }

                System.out.print(ints[i][j]);
            }
            System.out.println();
        }
    }
}
