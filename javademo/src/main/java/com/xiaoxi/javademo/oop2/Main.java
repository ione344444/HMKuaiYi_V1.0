package com.xiaoxi.javademo.oop2;

public class Main {
    public static void main(String[] args) {
        ChangFangXin changFangXin1 = new ChangFangXin();
        changFangXin1.chang = 21;
        changFangXin1.kuan = 13;

        ChangFangXin changFangXin2 = new ChangFangXin();
        changFangXin2.chang = 11;
        changFangXin2.kuan = 10;

        System.out.println("长方形1的面积：" + changFangXin1.chang*changFangXin1.kuan);
        System.out.println("长方形2的面积：" + changFangXin2.chang*changFangXin2.kuan);
    }
}
