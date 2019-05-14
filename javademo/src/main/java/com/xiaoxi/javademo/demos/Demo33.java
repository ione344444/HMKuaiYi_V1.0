package com.xiaoxi.javademo.demos;

import java.util.Arrays;

public class Demo33 {
    public static void main(String[] args) {
        int[] ints1 = new int[]{1,2,3,4,5};
        int[] ints2 = new int[]{10,20,30,40,50};

        int[] newInts = new int[ints1.length + ints2.length];

        for (int i = 0;i < ints1.length;i++){
            newInts[i] = ints1[i];
        }

        for (int i = 0;i < ints2.length;i++){
            newInts[i+ints1.length] = ints2[i];
        }

        System.out.println(Arrays.toString(newInts));
    }
}
