package com.pianma.bubble.ex13;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;


@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

    private BubbleFrame mContext;
    private int x;
    private int y;

    //적군 방향
    private EnemyDirection enemyDirection;


   private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;



    //적군 속도 상태
    private final int SPEED = 3;
    private final int JUMPSPEED = 1;

    private ImageIcon enemyR, enemyL;


    public Enemy(BubbleFrame mContext){
        this.mContext = mContext;
        initObject();
        initSetting();
       initBackgroundEnemyService();
    }

    private  void initBackgroundEnemyService(){
//        new Thread(new BackgroundEnemyService(this)).start();
    }
    private void initObject(){
        enemyR = new ImageIcon("image/enemyR.png");
        enemyL = new ImageIcon("image/enemyL.png");
    }

    private void initSetting(){
        x= 480;
        y = 178;

        left = false;
        right = false;
        up = false;
        down =false;

        enemyDirection = enemyDirection.RIGHT;
        setIcon(enemyR);
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
        enemyDirection = enemyDirection.LEFT;
        new Thread(()->  {
            left = true;
            while (left) {
                setIcon(enemyL);
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
        enemyDirection = enemyDirection.RIGHT;
        new Thread(()->  {
            right = true;
            while (right){
                setIcon(enemyR);
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
