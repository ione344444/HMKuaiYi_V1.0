package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo47 {
    public static void main(String[] args) {
        String reallyUserName = "userJAVA";
        String reallyPassword = "userJAVA";

        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名:");
        String userName = scanner.next();
        if (reallyUserName.equalsIgnoreCase(userName)){
            System.out.print("请输入密码：");
            String password = scanner.next();

            if (reallyPassword.equalsIgnoreCase(password)){
                System.out.println("登陆成功");
            }else {
                System.out.println("登录失败，密码错误");
            }
        }else {
            System.out.println("用户名错误");
        }
    }

}
