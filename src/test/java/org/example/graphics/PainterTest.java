package org.example.graphics;

import org.example.graphics.Camera;
import org.example.graphics.Painter;
import org.example.nodes.Point;
import org.example.utils.VectorCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PainterTest {
    private Painter painter;

    @BeforeEach
    public void setUp(){
        painter = new Painter(new Camera());
    }

    @Test
    void calculateIntersection() {
        //Given
        Vector<Double> line = new Vector<>(List.of(10.49,5.7,-1.74));
        Point point = new Point(4.58742,3.03995,0);
        Vector<Double> canvas = new Vector<>(List.of(1.,2.,3.,6.));
        //When
        Point actual = painter.calculateIntersection(line, point, canvas);
        //Then
        Point expected = new Point(-5.900893545290941, -2.659133623275344, 1.7397202639472105);
        assertEquals(expected,actual);
    }

    @Test
    void calculateCanvas() {
        //Given
        Point position = new Point(1.44, 2.51, 2.29);
        Vector<Double> orientation = new Vector<>(List.of(0.3911144725812, 0.6791562337331, 0.62110891115));
        //When
        Vector<Double> actual = painter.calculateCanvas(position, orientation);
        //Then
        Vector<Double> expected = new Vector<>(List.of(1.83,3.18,2.91, -21.98));
        // X = 0*Y + 0*Z + 1
        assertEquals(expected, actual);
    }

    @Test
    void getD() {
        //Given
        Vector<Double> normalVector = new Vector<>(List.of(1., 2., 3.));
        Point point = new Point(2., 4., 6.);
        //When
        double actual = painter.getD(point, normalVector);
        //Then
        double expected = 28;
        assertEquals(expected, actual);
    }

    @Test
    void testIsVisibleWhenFalse() {
        // given
        Vector<Double> vec1 = new Vector<>(List.of(0., 1., 0.));
        Vector<Double> vec2 = new Vector<>(List.of(0., 0., 1.));
        double angle = Math.PI / 3;
        // when
        boolean actual = painter.isVisible(vec1, vec2, angle);
        // then
        assertFalse(actual);
    }

    @Test
    void testIsVisibleWhenTrue() {
        // given
        Vector<Double> vec1 = new Vector<>(List.of(0., 1., 0.));
        Vector<Double> vec2 = new Vector<>(List.of(0., 0., 1.));
        double angle = Math.PI / 2 + 0.00001;
        // when
        boolean actual = painter.isVisible(vec1, vec2, angle);
        // then
        assertTrue(actual);
    }
}