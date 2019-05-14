package com.xiaoxi.javademo.demos;

public class Demo16 {
    public static void main(String[] args) {
        for (int i = 1;i<=15;i++){
            if (i % 7 == 0){
                System.out.println("遇到能被7整除的数");
                break;
            }
            System.out.println(i);
        }

        System.out.println("程序结束");
    }
}
