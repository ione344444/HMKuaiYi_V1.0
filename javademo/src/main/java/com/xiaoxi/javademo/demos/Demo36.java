package com.xiaoxi.javademo.demos;

public class Demo36 {
    public static void main(String[] args) {
        double[] doubles = new double[]{-1.0, -0.5, 0, 0.5, 1, 1.5, 2};

        double maxDouble = doubles[0];
        int maxDoubleIndex = 0;
        double minDouble = doubles[0];
        int minDoubleIndex = 0;

        for (int i = 1;i < doubles.length;i++){
            if (doubles[i] > maxDouble){
                maxDouble = doubles[i];
                maxDoubleIndex = i;
            }

            if (doubles[i] < minDouble){
                minDouble = doubles[i];
                minDoubleIndex = i;
            }
        }

        System.out.println("最大值：" + maxDouble + ",下标：" + maxDoubleIndex);
        System.out.println("最小值：" + minDouble + ",下标：" + minDoubleIndex);
    }
}
