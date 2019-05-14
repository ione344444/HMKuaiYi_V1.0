package com.xiaoxi.javademo.demos;

import java.util.Random;
import java.util.Scanner;

public class Demo12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true){
            System.out.println("游戏规则：系统会随机生成0-10范围内的随机数，" + "\n" +
                    "你需要猜测这个数，系统会根据你的猜测情况进行提示。" + "\n" +
                    "你只有5次机会。");
            System.out.println("准备好了吗？Y键继续，任意键退出");
            if (! scanner.next().equals("Y")){
                break;
            }

            int num = random.nextInt(10);
            int i = 0;
            while (true){
                i++;
                if (i==6){
                    System.out.println("你的机会已用完，正确答案是：" + num);
                    break;
                }

                System.out.print("请输入你猜测的数：");
                int c = scanner.nextInt();
                if (c > num){
                    System.out.println("猜大了");
                }else if (c < num){
                    System.out.println("猜小了");
                }else {
                    System.out.println("猜对了");
                    break;
                }
            }

            System.out.println("这一局已经结束，是否重新开局？(Y)");
            if (! scanner.next().equals("Y")){
                break;
            }
        }

        System.out.println("游戏结束");
    }
}
