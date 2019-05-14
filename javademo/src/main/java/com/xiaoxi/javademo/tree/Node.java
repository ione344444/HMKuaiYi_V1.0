package com.xiaoxi.javademo.tree;

public class Node {
    private Node leftNode,rightNode;

    private Student student;

    private int layNum;

    private DrawNode drawNode;

    public Node(int layNum,DrawNode drawNode){
        this.layNum = layNum;
        this.drawNode = drawNode;
    }

    public int getLayNum(){
        return layNum;
    }

    public void setLayNum(int layNum) {
        this.layNum = layNum;
    }

    public void add(Student student){

        if (getStudent() == null){
            this.student = student;

            leftNode = new Node(layNum+1,drawNode);
            rightNode = new Node(layNum+1,drawNode);

            System.out.println("add" + student.getNumber());
            return;
        }

        if (student.getNumber() == getStudent().getNumber()){
            this.student = student;
            return;
        }

        if (leftNode.getStudent() == null && rightNode.getStudent() == null){
            if (student.getNumber() < getStudent().getNumber()){
                leftNode.add(student);
            }else {
                rightNode.add(student);
            }
        }else if (leftNode.getStudent() != null && rightNode.getStudent() != null){
            if (student.getNumber() < getStudent().getNumber()){
                leftNode.add(student);
            }else {
                rightNode.add(student);
            }
        }else if (leftNode.getStudent() != null){
            if (student.getNumber() > getStudent().getNumber()){
                rightNode.add(student);
            }else if (student.getNumber() < leftNode.getStudent().getNumber()){
                rightNode.add(student);
                this.student = rightNode.getStudent();
                leftNode.setStudent(getStudent());
            }else if (student.getNumber() > leftNode.getStudent().getNumber()
                && student.getNumber() < getStudent().getNumber()) {
                rightNode.add(leftNode.getStudent());
                this.student = student;
            }else {
                leftNode.setStudent(student);
            }
        }else {
            if (student.getNumber() < getStudent().getNumber()){
                leftNode.add(student);
            }else if (student.getNumber() > rightNode.getStudent().getNumber()){
                leftNode.add(getStudent());
                this.student = rightNode.getStudent();
                rightNode.setStudent(student);
            }else if (student.getNumber() < leftNode.getStudent().getNumber()
                    && student.getNumber() > getStudent().getNumber()) {
                leftNode.add(getStudent());
                this.student = student;
            }else {
                rightNode.setStudent(student);
            }

        }
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Node find(int number){
        if (getStudent() == null){
            return null;
        }

        System.out.println("find");

        if (getStudent().getNumber() == number){
            return this;
        }else if (number < getStudent().getNumber()){
            return leftNode.find(number);
        }else {
            return rightNode.find(number);
        }
    }
}
