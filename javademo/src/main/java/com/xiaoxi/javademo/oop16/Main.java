package com.xiaoxi.javademo.oop16;

public class Main {
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.name = "周某人";
        driver.age = 32;
        driver.sex = '男';
        driver.drivingLicenceNumber = "3213189371736";
        driver.plateNumber = "陕·F1076";

        driver.toStringX();
        driver.driving();
        driver.stop();

        /*-************* >换行< ***************-*/
        System.out.println("\n");

        String name = "周末人";
        boolean isCauseTroubleDriver = driver.checkIsCauseTroubleDriver(name);
        System.out.println("是否是肇事司机" + name + "?" + isCauseTroubleDriver);

        String plateNumber = "湘·EE74A";
        boolean isDriverUsePlateNumber = driver.checkIsDriverUsePlateNumber(plateNumber);
        System.out.println("是否是车牌号为" + plateNumber + "的司机？" + isDriverUsePlateNumber);
    }
}
