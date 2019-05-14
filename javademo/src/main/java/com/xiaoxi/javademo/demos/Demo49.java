package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo49 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入你的身份证号：");

        String word = scanner.next();// 610724200305095573
        if (word.length() == 18){
            String y = word.substring(6,10);
            String m = word.substring(10,12);
            String d = word.substring(12,14);

            System.out.println("你的生日：" + y + "年" +
                    m + "月" + d + "日");
        }
    }
}
