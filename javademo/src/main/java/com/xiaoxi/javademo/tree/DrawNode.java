package com.xiaoxi.javademo.tree;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawNode extends JFrame {
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;

    private Color rectColor = new Color(0xf5f5f5);

    public DrawNode(){

    }

    public void draw(int layNum,int number){
        MyPanel mp=new MyPanel(layNum);
        this.add(mp);
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //定义一个MyPanel（我自己的面板，是用于绘图和显示绘图的区域）
    class MyPanel extends JPanel {
        private int layNum;

        public MyPanel(int layNum){
            this.layNum = layNum;
        }

        //覆盖JPanel的paint方法
        public void paint(Graphics g) {    //Graphics是绘图的重要类，可以理解成一只画笔
            //1、调用父类函数完成初始化（不可少）
            super.paint(g);

            g.setColor(Color.RED);
            for (int i = 1;i < 6;i++){
                layNum = i;
                g.drawRect(WINDOW_WIDTH/2 - (layNum - 1) * 100,100*layNum,50,50);
                g.drawLine(WINDOW_WIDTH/2 - (layNum - 1) * 100 + 25,100*layNum + 25,
                        WINDOW_WIDTH/2 - (layNum - 1) * 100 - 100 + 25,100*layNum + 100);

                g.drawLine(WINDOW_WIDTH/2 - (layNum - 1) * 100 + 25,100*layNum + 25,
                        WINDOW_WIDTH/2 - (layNum -1) * 100 + 100 + 25,100 * layNum +100);
            }
//            g.setFont(new Font("隶书", Font.BOLD, 30));
//            g.drawString("祖国万岁！", 100, 80);
        }
    }
}
