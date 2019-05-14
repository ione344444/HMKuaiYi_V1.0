package com.xiaoxi.javademo.demos;

import java.util.Arrays;

public class Demo38 {
    public static void main(String[] args) {
        int[] ints = new int[]{7,8,9,10};

        int numOfAdd = 5;
        int[] newInts = new int[ints.length+1];

        for (int i = 0;i < ints.length;i++){
            if(ints[i] > numOfAdd){
                if (i-1 < 0 || ints[i-1] <= numOfAdd){
                    newInts[i] = numOfAdd;
                }

                newInts[i+1] = ints[i];
            }else if (i == ints.length-1){
                newInts[newInts.length-1] = numOfAdd;
                newInts[newInts.length-2] = ints[i];
            }else {
                newInts[i] = ints[i];
            }
        }

        System.out.println(Arrays.toString(newInts));
    }
}
