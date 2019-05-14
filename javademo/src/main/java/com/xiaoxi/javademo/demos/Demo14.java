package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (String ok = "";! ok.equals("ok");ok = scanner.next()){
            System.out.println("练习跳舞");
            System.out.println("老师我练的OK吗？(ok):");
        }

        System.out.println("程序结束");
    }
}
