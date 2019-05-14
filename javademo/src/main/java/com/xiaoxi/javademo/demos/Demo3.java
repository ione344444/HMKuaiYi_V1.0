package com.xiaoxi.javademo.demos;

import java.util.Random;

public class Demo3 {
    public static void main(String[] args) {
        Random random = new Random();
        int num = 0;

        while (++num < 10){

            System.out.println(random.nextInt());
        }
    }
}
