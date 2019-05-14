package com.xiaoxi.javademo.oop12;

public class Shuxuejia {
    public String name;

    /**
     * 求两个数的最大值
     */
    public void printMaxInt(int i1, int i2){
        int max = i1 > i2 ? i1:i2;
        System.out.println("最大的数：" + max);
    }


    /**
     * 求三个小数的最小值
     */
    public void printMinDouble(double d1, double d2, double d3){
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

        System.out.println("最小的数：" + d1);
    }

    /**
     * 求一个数的个位数
     */
    public void printGewei(int i){
        int gewei = i % 10;
        System.out.println(i + "的个位数：" + gewei);
    }

    /**
     * 求一个数的立方
     */
    public void printNumX3(int i){
        int result = i*i*i;
        System.out.println(i + "的立方为：" + result);
    }


    /**
     * 求1+2+3+...+ num 的值
     * @param num
     */
    public void printAddToNum(int num){
        int result = 0;
        for (int i = 1;i <= num;i++){
            result += i;
        }

        System.out.println("1加到" + num + "的结果为：" + result);
    }

    /**
     * 计算数组元素的和
     */
    public void printHe(double[] doubles){
        double result = 0;
        for (double d : doubles) {
            result += d;
        }

        System.out.println("数组所有元素和为：" + result);
    }

    /**
     * 计算数组所有元素最大值
     */
    public void printMax(double[] doubles){
        double max = doubles[0];
        for (int i = 1;i < doubles.length;i++){
            if (doubles[i] > max){
                max = doubles[i];
            }
        }

        System.out.println("数组中最大的数为：" + max);
    }
}
