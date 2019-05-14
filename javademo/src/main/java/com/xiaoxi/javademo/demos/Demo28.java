package com.xiaoxi.javademo.demos;

public class Demo28 {
    public static void main(String[] args) {
        int[] nums = new int[5];

        for (int i=0;i<nums.length;i++){
            nums[i] = i+1;
        }

        int h = 0;
        for (int i=0;i<nums.length;i++){
            h += nums[i];
        }

        double p = h/(double)nums.length;

        System.out.println("数组所有元素的和：" + h);
        System.out.println("数组所有元素的平均值：" + p);
    }
}
