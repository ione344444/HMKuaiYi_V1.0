package com.xiaoxi.javademo.demos;

public class Demo26 {
    public static void main(String[] args) {
        int lineNum = 3;

        for (int i = 1;i <= lineNum;i++){
            for (int m = lineNum-i+1;m>=1;m--){
                System.out.print("*");
            }

            System.out.print("\n");
        }
    }
}
