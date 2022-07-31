package com.pianma.bubble.ex02;

import java.awt.*;
import javax.swing.*;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player;



    public BubbleFrame(){
        initObject();
        initSetting();
        setVisible(true);
    }

    private void initObject(){
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player = new Player();
        add(player);
//        backgroundMap.setSize(1000,600);
//        add(backgroundMap); //jframe jlabel이 그려진다.
    }

    private void initSetting(){
        setSize(1000, 640);
        setLayout(null);  //abosoulte layout
        setLocationRelativeTo(null); //jframe 가운데 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //jframe창을 끄면 jvm도 같이 끔
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
