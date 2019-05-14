package com.xiaoxi.javademo.oop15;

public class Main {
    public static void main(String[] args) {
        Pig pig = new Pig();
        pig.breed = "野猪";
        pig.color = "黑色";
        pig.fightCapacity = 5000;
        pig.weightKG = 300;

        pig.walk();
        pig.hurtPeople();

        pig.eatFood("红薯");

        double money = 30;
        int num = pig.buyLollypop(money);
        System.out.println("给猪" + money + "元，买回来" + num + "个棒棒糖");

        int numFeed = 10;
        double weight = pig.feedLollypop(numFeed);
        System.out.println("给猪吃了" + numFeed + "个棒棒糖，猪长了" + weight*2 + "斤");
    }
}
