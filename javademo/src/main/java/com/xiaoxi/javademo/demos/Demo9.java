package com.xiaoxi.javademo.demos;

public class Demo9 {
    public static void main(String[] args) {
        double p = 20;
        int y = 2010;
        while (p <= 100){
            p = p*1.25;
            y++;
        }
        System.out.println(y+"年超过100万人");
    }
}
