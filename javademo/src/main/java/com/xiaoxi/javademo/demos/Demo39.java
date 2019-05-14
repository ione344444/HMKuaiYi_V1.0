package com.xiaoxi.javademo.demos;

import java.util.Arrays;
import java.util.Random;

public class Demo39 {
    public static void main(String[] args) {
        int[] ints = new int[10];
        Random random = new Random();
        for (int i = 0;i < ints.length;i++){
            ints[i] = random.nextInt(10);
        }

        int max = ints[0];
        int min = ints[0];
        int h = 0;
        double p = 0;

        for (int i = 0;i < ints.length;i++){
            if (ints[i] > max){
                max = ints[i];
            }

            if (ints[i] < min){
                min = ints[i];
            }

            h += ints[i];
        }
        p = (double) h / ints.length;

        System.out.println(Arrays.toString(ints));
        System.out.print("最大值：" + max);
        System.out.println("最小值：" + min);
        System.out.println("和：" + h);
        System.out.println("平均值：" + p);
    }
}
