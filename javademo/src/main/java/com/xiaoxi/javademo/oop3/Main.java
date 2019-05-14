package com.xiaoxi.javademo.oop3;

public class Main {
    public static void main(String[] args) {
        Student zhaoritian = new Student();
        zhaoritian.name = "赵日天";
        zhaoritian.java = 60;
        zhaoritian.sql = 70;
        zhaoritian.csharp = 80;

        Student yeliangcheng = new Student();
        yeliangcheng.name = "叶凉城";
        yeliangcheng.java = 56;
        yeliangcheng.sql = 34;
        yeliangcheng.csharp = 90;

        double p1 = (zhaoritian.java + zhaoritian.sql + zhaoritian.csharp) / 3;
        double p2 = (yeliangcheng.java + yeliangcheng.sql + yeliangcheng.csharp) / 3;

        System.out.println("姓名：" + zhaoritian.name + "，平均分：" + p1);
        System.out.println("姓名：" + yeliangcheng.name + "，平均分：" + p2);
    }
}
