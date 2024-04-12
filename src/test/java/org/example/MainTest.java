package org.example;

import org.example.dataTypes.Figure;
import org.example.nodes.Edge;
import org.example.nodes.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void readFigure() {
        String line = "(0.0, 0.0, 0.0)&(0.0, 0.0, 1.0)-(0.0, 1.0, 0.0)&(1.0, 0.0, 0.0)-(0.0, 1.0, 0.0)";
        Point p1 = new Point(0.0, 0.0, 0.0);
        Point p2 = new Point(0.0, 0.0, 1.0);
        Point p3 = new Point(0.0, 1.0, 0.0);
        Point p4 = new Point(1.0, 0.0, 0.0);

        Edge e1 = new Edge(new Object[]{p2, p3});
        Edge e2 = new Edge(new Object[]{p4, p3});

        Figure f1 = new Figure(p1, List.of(e1, e2));
        Figure f2 = new Figure(line);

        assertEquals(f1, f2);
    }
}