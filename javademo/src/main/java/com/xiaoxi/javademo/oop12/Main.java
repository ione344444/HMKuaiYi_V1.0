package com.xiaoxi.javademo.oop12;

public class Main {
    public static void main(String[] args) {
        Shuxuejia shuxuejia = new Shuxuejia();
        shuxuejia.name = "张飞";
        shuxuejia.printMaxInt(3,2);
        shuxuejia.printMinDouble(2,4,1);
        shuxuejia.printNumX3(3);
        shuxuejia.printAddToNum(100);
        shuxuejia.printGewei(12);

        double[] doubles = new double[]{4.2,4.5,5.6,1.2,7.8,3.4};
        shuxuejia.printHe(doubles);
        shuxuejia.printMax(doubles);
    }
}
