package com.xiaoxi.javademo.demos;

import java.util.Arrays;
import java.util.Scanner;

public class Demo41 {
    public static void main(String[] args) {
        int[] ints = new int[]{5,15,25,35,45,55,65,75,85};

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要插入的数字：");
        int numOfAdd = scanner.nextInt();
        int[] newInts = new int[ints.length+1];

//        for (int i = 0;i < ints.length;i++){
//            if(ints[i] > numOfAdd){
//                if (i-1 < 0 || ints[i-1] <= numOfAdd){
//                    newInts[i] = numOfAdd;
//                }
//
//                newInts[i+1] = ints[i];
//            }else if (i == ints.length-1){
//                newInts[newInts.length-1] = numOfAdd;
//                newInts[i] = ints[i];
//            }else {
//                newInts[i] = ints[i];
//            }
//        }
//

        //1. 找位置（找下标）
        int index = 0;
        for(int i = 0; i < ints.length; i ++)
        {
            if(ints[i] > numOfAdd)
            {
                index = i;
                break;
            }

            if (i == ints.length-1){
                index = i+1;
            }
        }
        //2. 移动队列【旧队列->新队列】
        for(int i = 0; i < ints.length; i ++)
        {
            if(i < index)
            {
                newInts[i] = ints[i];
            }
            else  if (index == -1){
                newInts[newInts.length - 1] = numOfAdd;
                newInts[i] = ints[i];
            }
            else
            {
                newInts[i + 1] = ints[i];
            }
        }
        //3. 入列
        newInts[index] = numOfAdd;

        System.out.println(Arrays.toString(newInts));
    }
}
