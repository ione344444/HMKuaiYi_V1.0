package com.xiaoxi.javademo.demos;

import java.util.Random;

public class Demo34 {
    public static void main(String[] args) {
        int[] ints = new int[]{1,5,10,4,3,12,9,18};

        Random random = new Random();
        int num = random.nextInt(20);

        for (int i = 1;i < ints.length;i++){
            if (num == ints[i]){
                System.out.println("数组中包含随机生成的数：" + num);
                break;
            }else if (i == ints.length-1){
                System.out.println("数组中不包含随机生成的数：" + num);
            }
        }
    }
}
