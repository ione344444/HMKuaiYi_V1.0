package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int zuida = 0;
        int lastNum = 0;
//        while (true){
//            System.out.println("请输入一个整数:");
//            int num = scanner.nextInt();
//
//            if (num == 0){
//                break;
//            }
//
//            if (num > zuida){
//                zuida = num;
//            }
//        }

        System.out.println("请输入一个整数：");
        for (int i = scanner.nextInt();i != 0;i = scanner.nextInt()){
            zuida = i < lastNum ? lastNum : i;
            
            System.out.println("请输入一个整数：");
            lastNum = i;
        }

        System.out.println("最大的数：" + zuida);
    }
}
