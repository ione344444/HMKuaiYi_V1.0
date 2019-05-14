package com.xiaoxi.javademo.oop16;

public class Driver {
    public String name;
    public int age;
    public char sex;
    public String drivingLicenceNumber;
    public String plateNumber;

    public void toStringX(){
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("性别：" + sex);
        System.out.println("驾驶证号：" + drivingLicenceNumber);
        System.out.println("车牌号：" + plateNumber);
    }

    public void driving(){
        System.out.println(name + "司机正在开" + plateNumber + "车牌号的车");
    }

    public void stop(){
        System.out.println(name + "司机把" + plateNumber + "车牌号的车停了下来");
    }

    public boolean checkIsCauseTroubleDriver(String name){
        return name.equals(this.name);
    }

    public boolean checkIsDriverUsePlateNumber(String plateNumber){
        return plateNumber.equals(this.plateNumber);
    }
}
