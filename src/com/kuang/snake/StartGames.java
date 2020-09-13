package com.kuang.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
       //1.绘制一个静态窗口 jframe
        JFrame jFrame = new JFrame("贪吃蛇");
        //设置界面的大小
        jFrame.setBounds(10,10, 900,720);
        jFrame.setResizable(false); //窗口大小不可以改变
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭事件,游戏可以关闭
        //2. 面板Jpanel 可以加入到JFrame
        jFrame.add(new GamePannel());

        jFrame.setVisible(true); //让窗口能够展现出来

    }
}
