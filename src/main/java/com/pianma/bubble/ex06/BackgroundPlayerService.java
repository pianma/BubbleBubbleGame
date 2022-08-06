package com.pianma.bubble.ex06;

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
            image = ImageIO.read(new File("image/test.png"));
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
            //System.out.println("leftcolor : " + leftColor);
            //System.out.println("rightcolor : " + rightColor);

            if(leftColor.getRed() == 255 && leftColor.getGreen() ==0 && leftColor.getBlue() ==0){
                System.out.println("왼쪽 벽에 충돌");
            } else if (rightColor.getRed() ==255 && rightColor.getGreen() ==0 && leftColor.getBlue() ==0) {
                System.out.println("오른쪽 벽 충돌");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
