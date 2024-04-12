package org.example.utils;

import org.example.nodes.Point;

public class ResizeTool {
    private final int WIDTH;
    private final int HEIGHT;

    public ResizeTool(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public void resize(Point point) {
        point.setX(point.getX() * WIDTH  + (int) WIDTH / 2);
        point.setY(point.getY() * HEIGHT + (int) HEIGHT / 2);
    }
}
