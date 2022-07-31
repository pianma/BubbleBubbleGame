package com.pianma.bubble.ex01;

import javax.swing.*;

public class BubbleFrame extends JFrame {

    private JPanel panel1;
    private JButton button1;

    public BubbleFrame(){
        setSize(1000,640);
        getContentPane().setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
