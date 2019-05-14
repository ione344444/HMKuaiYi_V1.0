package com.xiaoxi.javademo.oop1;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.pinzhong = "纯种二哈";
        dog.tizhong = 58.5;
        dog.yanse = "黑白";

        System.out.println("品种：" + dog.pinzhong);
        System.out.println("体重(kg)：" + dog.tizhong);
        System.out.println("颜色：" + dog.yanse);
    }
}
