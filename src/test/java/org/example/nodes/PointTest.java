package org.example.nodes;

import org.example.nodes.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PointTest {
    @Test
    public void readFromString() {
        Point point = new Point("(1, 2, 3)");
        assertEquals(1, point.getX(), 0.000001);
        assertEquals(2, point.getY(), 0.000001);
        assertEquals(3, point.getZ(), 0.000001);
    }
}