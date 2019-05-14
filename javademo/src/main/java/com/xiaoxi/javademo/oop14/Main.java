package com.xiaoxi.javademo.oop14;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.name = "小周";
        student.java = 100;
        student.sql = 60;
        student.c = 23;

        student.learn();
        System.out.println("总成绩：" + student.getZong());
        System.out.println("平均成绩：" + student.getPinjun());
    }
}
