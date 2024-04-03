package org.example;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final Panel panel;
    private final int width = 500;
    private final int height = 500;

    public Frame() {
        super("My Frame");
        panel = new Panel(width, height);
        setSize(width, height);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public void paintPoint(double x, double y){
        panel.paintPoint(x, y);
    }

    public void paint(Graphics g){
        panel.paint(g);
    }
}
