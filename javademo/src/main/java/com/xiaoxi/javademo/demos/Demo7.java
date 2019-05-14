package com.xiaoxi.javademo.demos;

public class Demo7 {
    public static void main(String[] args) {
        int num = 10;
        do {
            if (num % 5 == 0){
                System.out.println(num);
            }
        }while (++num <=100);
    }
}
