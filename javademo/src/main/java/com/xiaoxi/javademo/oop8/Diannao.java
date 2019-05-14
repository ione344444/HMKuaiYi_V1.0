package com.xiaoxi.javademo.oop8;

public class Diannao {
    public double jiage;
    public String pinpai;
    public String madeDate;

    public void tongDian(){
        System.out.println("正在通电");
    }

    public void kaiJi(String moshi){
        tongDian();
        System.out.println("正在以" + moshi +"模式开机");
    }

    public void shutdown(){
        System.out.println("正在关机");
    }
}
