package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0;

        for (int i = 1;i<=5;i++){
            System.out.print("请输入成绩：");
            double fen = scanner.nextDouble();
            if (fen < 0){
                System.out.println("你输入了负分数");
                break;
            }

            result += fen;
        }

        System.out.println("总成绩：" + result);
    }
}
