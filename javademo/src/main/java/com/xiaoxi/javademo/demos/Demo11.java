package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("武松到井冈山喝酒。");
        String str;

        do {
            System.out.println("武松喝了一碗酒。");
            System.out.println("老板：还喝不？(y:喝)");
            str = scanner.next();
            System.out.println("武松：" + str);
        }while (str.equals("y"));
    }
}
