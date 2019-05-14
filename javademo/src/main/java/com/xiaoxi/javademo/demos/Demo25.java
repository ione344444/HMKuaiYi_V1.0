package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入你要打印的行数：");
        int lineNum = scanner.nextInt();

        System.out.println("请输入你要打印的列数：");
        int numForLine = scanner.nextInt();

        for (int i = 1;i <= lineNum;i++){
             for (int m = 1;m <= numForLine;m++){
                System.out.print("*");
            }

            System.out.print("\n");
        }
    }
}
