package com.xiaoxi.javademo.oop10;

public class Main {
    public static void main(String[] args) {
        Dianchi dianchi = new Dianchi();
        dianchi.pingpai = "杂牌";
        dianchi.dianliang = 44;

        Phone phone = new Phone();
        phone.dianchi = dianchi;
        phone.pinpai = "8848";
        phone.color = "黑色";
        phone.state = "关机";
        phone.kaiJi();
        phone.playGame();
        phone.chongDian();
        phone.bofangYinyue();
        phone.guanJi();
    }
}
