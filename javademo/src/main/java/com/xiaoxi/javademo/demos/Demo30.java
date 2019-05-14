package com.xiaoxi.javademo.demos;

import java.util.Arrays;

public class Demo30 {
    public static void main(String[] args) {
        double[] sz1 = new double[]{1.1,3.2,5.3,7.4,9.5};
        int[] sz2 = new int[sz1.length];

        for (int i = 0;i < sz2.length;i++){
            sz2[i] = (int) sz1[i];
        }

        System.out.println(Arrays.toString(sz2));
    }
}
