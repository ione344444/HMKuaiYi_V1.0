package com.xiaoxi.javademo.oop11;

public class Student {
    public double[] chengji;
    public double zongfen;

    public void jisuanZongfen(){
        if (zongfen <= 0){
            for (int i = 0;i < chengji.length;i++){
                zongfen += chengji[i];
            }
        }
    }
    public void pringZongfen() {
        jisuanZongfen();
        System.out.println("总分:" + zongfen);
    }

    public void printPinjunfen() {
        jisuanZongfen();
        System.out.println("平均分：" + zongfen/5);
    }
}
