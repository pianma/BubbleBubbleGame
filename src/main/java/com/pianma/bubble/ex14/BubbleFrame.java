package com.pianma.bubble.ex14;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter
@Setter
public class BubbleFrame extends JFrame {

    private BubbleFrame mContext = this;
    private JLabel backgroundMap;
    private Player player;
    private Enemy enemy;



    public BubbleFrame(){
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }

    private void initObject(){
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player = new Player(mContext);
        add(player);
        enemy = new Enemy(mContext);
        add(enemy);

    }

    private void initSetting(){
        setSize(1000, 640);
        setLayout(null);  //abosoulte layout
        setLocationRelativeTo(null); //jframe 가운데 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //jframe창을 끄면 jvm도 같이 끔
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft())
                            player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight())
                            player.right();
                        break;
                    case KeyEvent.VK_UP:
                        if (!player.isUp() && !player.isDown())
                            player.up();
                        break;
                    case KeyEvent.VK_SPACE:
                        player.attack();
                        break;
                }
            }

            //키보드 해제 이벤트 핸들러
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                }
            }
        });
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
