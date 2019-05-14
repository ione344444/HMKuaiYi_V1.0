package com.xiaoxi.javademo.tree;

public class Main {
    public static void main(String[] args) {
        Node node = new Node(1,new DrawNode());
        for (int i = 0;i < 5;i++){
            Student student = new Student("xx"+i,i);
            node.add(student);
        }

        System.out.println(node.find(4).getLayNum()+ "  " + node.find(0).getStudent().getName());
    }
}
