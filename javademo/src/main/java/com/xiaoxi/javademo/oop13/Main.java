package com.xiaoxi.javademo.oop13;

public class Main {
    public static void main(String[] args) {
        Shuxuejia shuxuejia = new Shuxuejia();
        shuxuejia.name = "张飞";

        System.out.println(shuxuejia.getMaxInt(2,3));
        System.out.println(shuxuejia.getMinDouble(4,2,3));
        System.out.println(shuxuejia.getAddToNum(100));
        System.out.println(shuxuejia.getGewei(125));
        System.out.println(shuxuejia.getNumX3(3));

        double[] doubles = new double[]{4.2,4.5,5.6,1.2,7.8,3.4};
        System.out.println(shuxuejia.getMax(doubles));
        System.out.println(shuxuejia.getHe(doubles));
    }
}
