package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double result = 0;

        for (int i = 1;i <= 5;i++){
            System.out.println("请输入第"+ i +
                    "名学生的Java成绩，Html成绩，C#成绩（以空格隔开）：");
            double java = scanner.nextDouble();
            double html = scanner.nextDouble();
            double csharp = scanner.nextDouble();

            double z = java+html+csharp;
            result += z;

            System.out.println("这名同学的总成绩:" + z);
        }

        System.out.println("\n这5名同学的总成绩:" + result);
    }
}
