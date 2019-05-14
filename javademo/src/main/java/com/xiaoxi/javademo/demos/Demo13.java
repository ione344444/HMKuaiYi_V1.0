package com.xiaoxi.javademo.demos;

public class Demo13 {
    public static void main(String[] args) {
        int result = 0;

        for (int i = 10;i<=100;i++){
            if (i%7 == 0){
                System.out.println(i);
                result += i;
            }
        }

        System.out.println("最终的和："+result);
    }
}
