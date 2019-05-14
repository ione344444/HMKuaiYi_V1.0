package com.xiaoxi.javademo.oop9;

public class Zhazhiji {
    public double jiage;
    public String pinpai;

    public void tongDian(){
        System.out.println("正在通电");
    }

    public void kaiJi(){
        tongDian();
        System.out.println("正在开机");
    }

    public void zhaZhi(String shuiguo){
        System.out.println("正在榨" + shuiguo + "果汁");
    }
}
