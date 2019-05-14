package com.xiaoxi.javademo.oop15;

public class Pig {
    // 品种
    public String breed;
    // 颜色
    public String color;
    // 战斗力
    public int fightCapacity;
    // 以kg为单位，猪的体重
    public double weightKG;

    public void walk(){
        System.out.println(breed + "品种的" + color + "颜色的猪走来走去");
    }

    public void hurtPeople(){
        System.out.println(breed + "品种的" + color + "颜色的猪打人了，战斗力为" + fightCapacity);
    }

    public void eatFood(String food){
        System.out.println(breed + "品种的" + color + "颜色的猪在吃" + food);
    }

    public double getPrice(){
        // 16元一斤,战斗力有加成
        return weightKG * 32 + 5 * fightCapacity;
    }

    public int buyLollypop(double money){
        // 5毛钱一个棒棒糖
        return (int) (money /  0.5);
    }

    public double feedLollypop(int num){
        // 吃一个棒棒糖长2.5kg
        return num * 2.5;
    }
 }
