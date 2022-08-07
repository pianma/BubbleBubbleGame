package com.pianma.bubble.ex11;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;


@Getter
@Setter
public class Player extends JLabel implements Moveable {

    private int x;
    private int y;

    //플레이어의 방향
    private PlayerDirection playerDirection;


   private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    //벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    //플레이어 속도 상태
    private final int SPEED = 4;
    private final int JUMPSPEED = 2;

    private ImageIcon playerR, playerL;


    public Player(){
        initObject();
        initSetting();
        initBackgroundService();
    }

    private  void initBackgroundService(){
        new Thread(new BackgroundPlayerService(this)).start();
    }
    private void initObject(){
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }

    private void initSetting(){
        x= 80;
        y = 535;

        left = false;
        right = false;
        up = false;
        down =false;

        playerDirection = playerDirection.RIGHT;
        setIcon(playerR);
        setSize(50,50);
        setLocation(x, y);
    }

    @Override
    public void up() {
        up = true;
        new Thread(() -> {

            for (int i = 0; i < 130/JUMPSPEED; i++) {
                y = y - (JUMPSPEED);
                setLocation(x, y);
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    System.out.println("위쪽 이동중 인터럽트 발생 : " + e.getMessage());
                }
            }

            up = false;
            down();
        }).start();

    }

    @Override
    public void down() {
        System.out.println("하강중");
        down = true;
        new Thread(() -> {
            while (down) {
                y = y + (JUMPSPEED);
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (Exception e) {
                    System.out.println("아래쪽 이동중 인터럽트 발생 : " + e.getMessage());
                }
            }

        }).start();
    }

    @Override
    public void left() {
        System.out.println("left");
        playerDirection = playerDirection.LEFT;
        new Thread(()->  {
            left = true;
            while (left) {
                setIcon(playerL);
                x = x - SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    @Override
    public void right() {
        System.out.println("right");
        playerDirection = playerDirection.RIGHT;
        new Thread(()->  {
            right = true;
            while (right){
                setIcon(playerR);
                x= x+SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
