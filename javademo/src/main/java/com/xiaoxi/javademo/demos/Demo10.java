package com.xiaoxi.javademo.demos;

import java.util.Random;

public class Demo10 {
    public static void main(String[] args) {
        Random random = new Random();
        int i = 0;
        while (i++ < 13){
            int num = random.nextInt(13)+1;

            switch (num){
                case 11:
                    System.out.println("输出一张红桃J");
                    break;

                case 12:
                    System.out.println("输出一张红桃Q");
                    break;

                case 13:
                    System.out.println("输出一张红桃K");
                    break;

                default:
                    System.out.println("输出一张红桃" + num);
                    break;
            }
        }
    }
}
