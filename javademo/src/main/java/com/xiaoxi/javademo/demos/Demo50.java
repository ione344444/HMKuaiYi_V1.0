package com.xiaoxi.javademo.demos;

import java.util.Arrays;
import java.util.Scanner;

import sun.rmi.runtime.Log;

public class Demo50 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入邮箱：");
        String mail = scanner.next();// luomuxiaoxix@gmail.com

        String[] strs1 = mail.split("@");
        String[] strs2 = mail.split("\\.");

        if (strs1.length > 2 || strs1.length < 2){// 没有@或者有多个@
            System.out.println("有且只能有一个@");
        }else {
            if (strs1[0].equals("")) {// @如果在最前面
                System.out.println("@不能在最前面");
            }else {
                if (strs2.length > 2 || strs2.length < 2){// 没有.或者有多个.
                    System.out.println("有且只能有一个.");
                }else {
                    if (strs2[strs2.length - 1].equals("")){// .在末尾
                        System.out.println(".不能在末尾");
                    }else {
                        System.out.println("注册成功");
                    }
                }
            }
        }

        if (strs1.length > 2 || strs1.length < 2){

        }
    }
}
