package com.xiaoxi.javademo.demos;

public class Demo27 {
    public static void main(String[] args) {
        int[] sz = new int[]{1, 2, 3, 0};
        sz[0] = 1;
        sz[1] = sz[0] + 1;
        sz[2] = sz[0] + sz[1] + 1;
        sz[3] = sz[0] + sz[1] + sz[2] + 1;
//        sz[4] = sz[0] + sz[1] + sz[2] + sz[3] + 1;
        System.out.print("数组元素值：" + sz[0] + "\t" + sz[1] + "\t" + sz[2] + "\t" + sz[3]);
    }
}
