package com.xiaoxi.javademo.demos;

import java.util.Arrays;

/**
 * Java冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,7,49,5,29,1,35};

        for (int i = 0;i < nums.length;i++) {
            // 循环比较数组中相邻的两个元素，大的放后面，从第一个开始，以此类推
            // 这样最大的数就会在数组的末尾，重复循环，从后往前依次缩小循环范围，
            // 直到第一个元素
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int var = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = var;
                }
            }
        }

        System.out.println(Arrays.toString(nums));
    }
}
