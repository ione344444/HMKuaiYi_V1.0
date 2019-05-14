package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        // 过滤非法字符
        if (! scanner.hasNextInt()){
            return;
        }

        int num = scanner.nextInt();
        if (num < 1){
            System.out.println("程序结束");
        }

        int i = 1;
        int result = 0;
        while (i<=num){
            result = result + i;

            if (i==num){
                System.out.print(i+"=");
            }else {
                System.out.print(i+"+");
            }
            i++;
        }

        System.out.println(result);
    }
}

