package com.kuang.snake;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel {
    //画板： 画界面, 画蛇
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK); //设置背景的颜色
    }
}
