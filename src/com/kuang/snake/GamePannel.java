package com.kuang.snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePannel extends JPanel implements KeyListener, ActionListener {
    int length;//蛇的长度
    int[] snakeX = new int[600]; //蛇的坐标x
    int[] snakeY = new int[500]; //蛇的坐标y
    String fx="R"; //蛇的方向 ： R:右  L:左  U:上  D:下
    boolean isStart = false; //游戏是否开始
    Timer timer = new Timer(100,this); //定时器
    //1.定义一个食物
    int foodx;
    int foody;
    Random random = new Random();

    //死亡判断
    boolean isFail = false;
    //积分系统
    int score;

    //构造器 初始化
    public GamePannel(){
       init();
       //获取键盘的监听事件
        this.setFocusable(true); //键盘的焦点聚集在游戏上
        this.addKeyListener(this); //实现监听
        timer.start();//让时间动起来
    }

    //初始化方法
    public void init(){
       length = 3;
       snakeX[0] = 100; snakeY[0] = 100; //头部坐标
        snakeX[1] = 75; snakeY[1] = 100; //第二个身体坐标
        snakeX[2] = 50; snakeY[2] = 100; //第三个身体坐标
        //初始化一个食物
        foodx = 25 + 25*random.nextInt(34);
        foody = 75 + 25*random.nextInt(24);
        score = 0;
    }
    //画板： 画界面, 画蛇
    //Graphics 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏
        this.setBackground(Color.WHITE); //设置背景的颜色
        //绘制头部广告栏
        Data.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,600);

        //画一条静态的蛇
        //蛇头
        if (fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0], snakeY[0]);
        }else if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0], snakeY[0]);
        }else if(fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0], snakeY[0]);
        }else if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0], snakeY[0]);
        }
        //蛇身
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i], snakeY[i]); //蛇的长度通过length控制
        }
        //画食物
        Data.food.paintIcon(this,g,foodx,foody);


        //游戏提示：是否开始
        if(isStart==false){
            //画一个文字, String
            g.setColor(Color.WHITE);//设置画笔的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD,40)); //设置字体
            g.drawString("按下空格开始游戏", 300, 300);
        }


        //失败提醒
        if(isFail==true){
            //画一个文字，String
            g.setColor(Color.RED);//设置画笔的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD,40)); //设置字体
            g.drawString("游戏失败，按下空格重新开始", 200, 300);
        }
        //画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD,18)); //设置字体
        g.drawString("长度 "+length, 750, 35);
        g.drawString("分数 "+score, 750, 50);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起:敲击
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }

    @Override
    //按收键盘的输入：监听
    public void keyPressed(KeyEvent e) {
        //键盘按下未释放
        //获取按下的键盘是哪个键
        int keycode = e.getKeyCode();
        if(keycode==KeyEvent.VK_SPACE){
            if(isFail){
                //失败，游戏再来一边
                isFail=false;
                init();//重新初始化游戏
            }else {
                isStart = !isStart;
            }
            repaint(); //重新绘制界面，也就是刷新界面
        }
        if(keycode==KeyEvent.VK_LEFT){
            fx = "L";
        } else if(keycode==KeyEvent.VK_RIGHT){
            fx = "R";
        } else if(keycode==KeyEvent.VK_UP){
            fx = "U";
        }else if(keycode==KeyEvent.VK_DOWN){
            fx = "D";
        }
    }

    //定时器，监听时间, 帧：执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态,并且游戏没有结束
        if(isStart==true && isFail==false){
            //右移
            for (int i = length-1; i > 0; i--) { //除了脑袋，身体都向前移动
               snakeX[i] = snakeX[i-1];
               snakeY[i] = snakeY[i-1];
            }
            //通过控制方向让头部移动
            if(fx.equals("R")){
                snakeX[0] = snakeX[0]+25; //头部移动
                //边界判断
                if(snakeX[0] > 850)
                    snakeX[0] = 25;

            }else if(fx.equals("L")){
                snakeX[0] = snakeX[0]-25; //头部移动
                //边界判断
                if(snakeX[0] < 25)
                    snakeX[0] = 850;

            }else if(fx.equals("U")){
                snakeY[0] = snakeY[0]-25;
                if (snakeY[0]<75)
                    snakeY[0] = 650;

            }else if(fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 650)
                    snakeY[0] = 75;
            }

            //如果小蛇的头和食物重合
            if(snakeX[0]==foodx && snakeY[0]==foody){
                //长度+1
                length++;

                score+=10;

                //重新生成食物
                foodx = 25 + 25*random.nextInt(34);
                foody = 75 + 25*random.nextInt(24);
            }
            //结束判断
            for (int i = 1; i < length; i++) {
               if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                   isFail = true;
               }
            }
            //刷新界面
            repaint();
        }
        timer.start();//让时间动起来

    }

}
