package com.xiaoxi.javademo.demos;

import java.util.Arrays;

public class Demo40 {
    public static void main(String[] args) {
        int[] ints = new int[]{4,5,3,1,9};

        int num = 3;
        int numTo = 9;
        int index = 0;

        boolean isChanged = false;
        for (int i = 0;i < ints.length;i++){
            if (num == ints[i]){
                ints[i] = numTo;
                index = i;
                isChanged = true;
            }
        }
        if (isChanged){
            System.out.println("将下标为" + index + "的元素由" + num + "改为" + numTo + "：");
            System.out.println(Arrays.toString(ints));
        }else {
            System.out.println("未找到值为" + num + "的元素");
        }
    }
}
