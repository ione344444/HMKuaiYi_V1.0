package com.xiaoxi.javademo.demos;

import java.util.Arrays;

public class Demo31 {
    public static void main(String[] args) {
        int[] ints = new int[100];
        int result = 0;

        // 将1到100放进数组
        for(int i = 1;i <= 100;i++) {
            ints[i-1] = i;
        }

        // 求数组所有元素的和
        for (int i = 0;i < ints.length;i++){
            result += ints[i];
        }

//        System.out.println(Arrays.toString(ints));
        System.out.println("和："+result);
    }
}
