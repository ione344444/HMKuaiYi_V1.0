package com.xiaoxi.javademo.demos;

public class Demo17 {
    public static void main(String[] args) {
        for (int i = 0;i <= 15;i++){
            if (i % 7 == 0){
                continue;
            }

            System.out.println(i);
        }
    }
}
