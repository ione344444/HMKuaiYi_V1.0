package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo35 {
    public static void main(String[] args) {
        String[] strs = new String[5];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0;i < strs.length;i++){
            System.out.print("请输入第"+ (i+1) +"个数组元素：");
            strs[i] = scanner.next();
        }

        System.out.print("\n请输入你要查找的字符串：");
        String findStr = scanner.next();

        for (int i = 0;i < strs.length;i++){
            if (strs[i].equals(findStr)){
                System.out.println("找到了！下标为：" + i);
                break;
            }else if (i == strs.length-1){
                System.out.println("未找到");
            }
        }
    }
}
