package com.pianma.bubble.ex10;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//메인스레드 바쁨 -  키보드 이벤트를 처리하기 바쁨
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;
    private Player player;

    public BackgroundPlayerService(Player player){
        this.player = player;
        try {
            image = ImageIO.read(new File("image/backgroundMap.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run(){
        while (true){
            //색상 확인

            Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));

            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
                    + image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);

            if (bottomColor != -2) {
                player.setDown(false);
            } else if (!player.isUp() && !player.isDown()) {
                player.down();
            }

            if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen() == 0) {
                player.setLeft(false);
                player.setLeftWallCrash(true);
            } else if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen() == 0) {
                player.setRight(false);
                player.setRightWallCrash(true);
            } else {
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
