package com.pianma.bubble.ex14;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

    //의존성 컴포지션
    private BubbleFrame mContext;
    private Player player;
    private Enemy enemy;
    private BackgroundBubbleService backgroundBubbleService;


    private int x;
    private int y;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;


    //적군을 맞춘 상태
    private int state; // 0(물방울), 1(적을 가둔 물방울)
    private ImageIcon bubble; //물방율
    private ImageIcon bubbled;  //적을 가둔 물방울
    private  ImageIcon bomb; //물방울 터진 상태

    public Bubble(BubbleFrame mContext){
        this.mContext = mContext;
        this.player = mContext.getPlayer();
        this.enemy = mContext.getEnemy();
        initObject();
        initSetting();
    }
    private void initObject(){
        bubble = new ImageIcon(("image/bubble.png"));
        bubbled = new ImageIcon(("image/bubbled.png"));
        bomb = new ImageIcon(("image/bomb.png"));
       backgroundBubbleService = new BackgroundBubbleService(this);
    }
    private void initSetting(){
        up = false;
        right = false;
        left = false;

       x=player.getX();
       y=player.getY();

       setIcon(bubble);
       setSize(50,50);

       state =0;

    }

    @Override
    public void up() {
        up = true;
        while(up){
            y--;
            setLocation(x, y);
            if(backgroundBubbleService.topWall()){
                up = false;
                break;
            }
            try {
                Thread.sleep(1);
            }catch  (InterruptedException e){
                e.printStackTrace();
            }
        }
        clearBubble();
    }

    @Override
    public void attack() {
        state = 1;
        setIcon(bubbled);
    }

    public void clearBubble(){
        try {
            Thread.sleep(3000);
            setIcon(bomb);
            Thread.sleep(500);
            mContext.remove(this);
            mContext.repaint();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void left() {
        left = true;
        for(int i=0; i<400;i++){
            x--;
            setLocation(x, y);

            if(backgroundBubbleService.leftWall()){
                left = false;
                break;
            }

            if(Math.abs(x - enemy.getX()) > 40 && Math.abs(x - enemy.getX()) < 60 &&
                    Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50){
                System.out.println("물방울이 적군과 충돌됨");
                attack();
           }

            try {
                Thread.sleep(1);
            }catch  (InterruptedException e){
                e.printStackTrace();
            }
        }
        up();
    }

    @Override
    public void right() {
        right = true;
        for(int i=0; i<400;i++){
            x++;
            setLocation(x, y);
            if(backgroundBubbleService.rightWall()){
                right = false;
                break;
            }
            try {
                Thread.sleep(1);
            }catch  (InterruptedException e){
                e.printStackTrace();
            }
        }
        up();
    }
}
