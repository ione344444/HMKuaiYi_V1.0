package com.xiaoxi.javademo.demos;

public class Demo32 {
    public static void main(String[] args) {
        char[] chars = new char[]{'a','b','c','d','e'};
        char[] newChars = new char[chars.length];

        for (int i = 0;i < newChars.length;i++){
            newChars[i] = chars[i];
        }

        for (int i = newChars.length-1;i >= 0;i--){
            System.out.print(newChars[i] + " ");
        }

    }
}
