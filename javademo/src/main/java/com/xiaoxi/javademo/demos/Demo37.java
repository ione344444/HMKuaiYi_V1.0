package com.xiaoxi.javademo.demos;

import java.util.Random;
import java.util.Scanner;

public class Demo37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要创建的数组长度：");
        int num = scanner.nextInt();

        if (num <= 0){
            System.out.println("输入有误，程序结束");
            return;
        }

        int[] sz = new int[num];
        for (int i = 0;i < sz.length;i++){
            System.out.print("\n请输入第" + (i+1) + "个元素：");
            sz[i] = scanner.nextInt();
        }

        Random random = new Random();
        int rand = random.nextInt(100);
        for (int i = 0;i < sz.length;i++){
            if (sz[i] == rand){
                System.out.println("\n数组中包含随机生成的数:" + rand);
            }else if (i == sz.length-1){
                System.out.println("\n数组中不包含随机生成的数：" + rand);
            }
        }
    }
}
