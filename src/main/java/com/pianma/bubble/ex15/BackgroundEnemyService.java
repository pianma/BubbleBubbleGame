package com.pianma.bubble.ex15;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

//메인스레드 바쁨 -  키보드 이벤트를 처리하기 바쁨
//백그라운드에서 계속 관찰
public class BackgroundEnemyService implements Runnable{

    private BufferedImage image;
    private Enemy enemy;

    public BackgroundEnemyService(Enemy enemy) {
        this.enemy = enemy;
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (enemy.getState() == 0) {

            try {

                Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
                Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 10, enemy.getY() + 25));

                int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + 50 + 5)
                        + image.getRGB(enemy.getX() + 50 - 10, enemy.getY() + 50 + 5);

                if (bottomColor != -2) {
                    enemy.setDown(false);
                } else if (!enemy.isUp() && !enemy.isDown()) {
                    enemy.down();
                }

                if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen() == 0) {
                    System.out.println("왼쪽충돌");
                    enemy.setLeft(false);
                    if (!enemy.isRight()) {
                        enemy.right();
                    }

                } else if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen() == 0) {
                    System.out.println("오른쪽충돌");
                    enemy.setRight(false);
                    if (!enemy.isLeft()) {
                        enemy.left();
                    }
                }

                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

    }

}
