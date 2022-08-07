package com.pianma.bubble.ex11;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

    //의존성 컴포지션
    private Player player;


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

    public Bubble(Player player){
        this.player = player;
        initObject();
        initSetting();
        initThread();
    }
    private void initObject(){
        bubble = new ImageIcon(("image/bubble.png"));
        bubbled = new ImageIcon(("image/bubbled.png"));
        bomb = new ImageIcon(("image/bomb.png"));
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

    private void initThread(){
        //버블은 스레드가 하나만 필요
        new Thread(()->{
        if(player.getPlayerDirection() == PlayerDirection.LEFT){
            left();
        }else{
            right();
        }

        }).start();
    }

    @Override
    public void up() {
        up = true;
        while(up){
            y--;
            setLocation(x, y);

            try {
                Thread.sleep(1);
            }catch  (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void left() {
        left = true;
        for(int i=0; i<400;i++){
            x--;
            setLocation(x, y);

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

            try {
                Thread.sleep(1);
            }catch  (InterruptedException e){
                e.printStackTrace();
            }
        }
        up();
    }
}
