package com.kuang.snake;

import javax.swing.*;
import java.net.URL;

//存放外部的数据
public class Data {
    //头部的图片 URL:定位图片地址 ImageIcon:图片
    public static URL headURL =
            Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headURL);
    //蛇头up
    public static URL upURL =
            Data.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upURL);
    //蛇头down
    public static URL downURL =
            Data.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downURL);
    //蛇头up
    public static URL leftURL =
            Data.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);
    //蛇头up
    public static URL rightURL =
            Data.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);

    //身体
    public static URL bodyURL =
            Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    //食物
    public static URL foodURL =
            Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);

}
