package org.example;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {
    @Test
    public void testEdgeConstructor() {
        Point point1 = new Point(1, 2, 3);
        Point point2 = new Point(4, 5, 6);
        String[] args = {"(1, 2, 3)", "(4, 5, 6)"};
        Edge edge = new Edge(args);
        assertEquals(point1, edge.getPoints().get(0));
        assertEquals(point2, edge.getPoints().get(1));
    }
}