package com.xiaoxi.javademo.oop8;

public class Main {
    public static void main(String[] args) {
        Diannao diannao = new Diannao();
        diannao.jiage = 30000;
        diannao.pinpai = "败家之眼";
        diannao.madeDate = "2018.10.13";

        diannao.kaiJi("爆炸倒计时");
        diannao.shutdown();
    }
}
