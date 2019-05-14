package com.xiaoxi.javademo.demos;

import java.util.Map;

public class Demo1 {
//    public static void main(String[] args)
//    {
//        int a = 1;
//        while(a <= 100)
//        {
//            System.out.println("已运行的循环次数：" + a);
//            a++;
//        }
//    }

    public static void main(String[] args){
        String str1 = new String("Accp");
        String str2 = new String("Benet");
        str2 = str1.toUpperCase().concat(str2);
        str2 = str2.substring(2, 5);
        System.out.println(str2);

//        int i = 0;
//        while( ++i <=10){
//            // 循环体语句
//            System.out.println("<");
//        }
//         i = 0;
//        do{
//            // 循环体语句
//            System.out.println(">");
//        }while( ++i <=10);
//
        String s1 = "hello";
        String s2 = new String("hello");
        StringBuffer s3 = new StringBuffer("hello");
        System.out.println(5.5 % 3.5);
    }

}
