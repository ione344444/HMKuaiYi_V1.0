package com.xiaoxi.javademo.demos;

import java.util.Scanner;

public class Demo48 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入你的用户名（6-18位）：");
        String userName = scanner.next();

        if (userName.length() >= 6 && userName.length() <= 18){
            System.out.print("请输入一个密码（长度不能小于10）：");
            String password = scanner.next();

            if (password.length() >= 10){
                System.out.println("注册成功！");
            }else {
                System.out.println("密码输入有误，密码长度不能小于10");
            }
        }else {
            System.out.println("用户名输入有误，长度必须在6-18");
        }
    }
}
