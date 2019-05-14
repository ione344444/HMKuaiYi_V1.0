package com.xiaoxi.javademo.oop14;

public class Student {
    public String name;

    public double java;
    public double sql;
    public double c;


    public void learn(){
        System.out.println(name+"同学在学习");
    }

    public double getZong(){
        return java + sql + c;
    }

    public double getPinjun(){
        return getZong() / 3;
    }
}
