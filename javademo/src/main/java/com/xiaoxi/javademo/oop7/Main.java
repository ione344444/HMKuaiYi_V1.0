package com.xiaoxi.javademo.oop7;

public class Main {
    public static void main(String[] args) {
        Dog a = new Dog();
        a.name = "花花公子";
        a.color = "五颜六色";
        a.weight = 200;
        a.printDogInfo();

        a = new Dog();
        a.name = "黑熊";
        a.color = "颜色";
        a.weight = 300;
        a.printDogInfo();
    }
}
