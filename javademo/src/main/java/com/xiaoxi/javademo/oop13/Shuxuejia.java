package com.xiaoxi.javademo.oop13;

public class Shuxuejia {
    public String name;

    public int getMaxInt(int i1, int i2){
//        int max = 0;
//        if(){
//            return i1;
//        }else {
//            return i2;
//        }
        return i1 > i2 ? i1:i2;
    }

    public double getMinDouble(double d1, double d2, double d3){
        if (d1 > d2){
            double var = d1;
            d1 = d2;
            d2 = var;
        }

        if (d1 > d3){
            double var = d1;
            d1 = d3;
            d3 = var;
        }

        return d1;
    }

    public int getGewei(int i){
        return i % 10;
    }

    public int getNumX3(int i){
        return i*i*i;
    }

    public int getAddToNum(int num){
        int result = 0;
        for (int i = 1;i <= num;i++){
            result += i;
        }

        return result;
    }

    public double getHe(double[] doubles){
        double result = 0;
        for (double d : doubles) {
            result += d;
        }

        return result;
    }

    public double getMax(double[] doubles){
        double max = doubles[0];
        for (int i = 1;i < doubles.length;i++){
            if (doubles[i] > max){
                max = doubles[i];
            }
        }

        return max;
    }
}
