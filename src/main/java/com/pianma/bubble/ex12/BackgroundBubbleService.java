package com.pianma.bubble.ex12;

import com.pianma.bubble.ex12.Bubble;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//메인스레드 바쁨 -  키보드 이벤트를 처리하기 바쁨
//백그라운드에서 계속 관찰
public class BackgroundBubbleService{

    private BufferedImage image;
    private Bubble bubble;

    public BackgroundBubbleService(Bubble bubble){
        this.bubble = bubble;
        try {
            image = ImageIO.read(new File("image/backgroundMap.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean leftWall(){
        Color leftColor = new Color(image.getRGB(bubble.getX() - 10, bubble.getY() + 25));
        if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen() == 0) {
          return true;

        }
        return false;
    }

    public boolean rightWall(){
        Color rightColor = new Color(image.getRGB(bubble.getX() + 50 + 10, bubble.getY() + 25));
         if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen() == 0) {
           return true;

        }
        return false;
    }

    public boolean topWall(){
        Color topColor = new Color(image.getRGB(bubble.getX()+25, bubble.getY()-10));
        if (topColor.getRed() == 255 && topColor.getBlue() == 0 && topColor.getGreen() == 0) {
            return true;

        }
        return false;
    }
}
