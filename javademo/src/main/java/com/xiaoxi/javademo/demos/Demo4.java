package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入消费金额:");
//        if (! scanner.hasNextDouble()){
//            System.out.println("输入的内容不合法");
//            return;
//        }

        double money = scanner.nextDouble();

        System.out.println("请选择下面的活动:");
        int which = scanner.nextInt();
        switch (which){
            case 0:
                System.out.println("没参加");
                break;

            case 1:
                if (money >= 50){
                    System.out.println("总金额:" + (money + 2));
                }else {
                    System.out.println("滚");
                }
                break;

            case 2:
                if (money >= 100){
                    System.out.println("总金额:" + (money + 3));
                }else {
                    System.out.println("滚");
                }
                break;

            case 3:
                if (money >= 100){
                    System.out.println("总金额:" + (money + 10));
                }else {
                    System.out.println("滚");
                }
                break;

            case 4:
                if (money >= 200){
                    System.out.println("总金额:" + (money + 10));
                }else {
                    System.out.println("滚");
                }
                break;

            case 5:
                if (money >= 200){
                    System.out.println("总金额:" + (money + 20));
                }else {
                    System.out.println("滚");
                }
                break;
        }
//        switch ((int)money / 10){
//            case 20:
//                System.out.println("4:满200加10");
//                System.out.println("5：满200加20");
//
//            case 10:
//                System.out.println("3：满100加10");
//                System.out.println("2: 满100加3");
//
//            case 5:
//                System.out.println("1：满50加2");
//                break;
//
//            default:
//                break;
//        }

    }
}
