package org.example;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{
    public Panel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(100, 100, 10, 10);
    }

    public void paintPoint(double x, double y) {
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.setColor(Color.BLACK);
        g2.fillOval((int) x, (int) y, 10, 10);
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        paintPoint(100, 100);
        g2.drawLine(0, 0, 500, 500);
    }
}
