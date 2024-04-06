package org.example;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PainterTest {
    private Painter painter;

    @BeforeEach
    public void setUp(){
        painter = new Painter();
    }

    @Test
    void isVisible() {
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
    void calculateLine() {
    }

    @Test
    void calculateCanvas() {
        //Given
        Camera camera = new Camera();
        //When
        Vector<Double> actual = painter.calculateCanvas(camera);
        //Then
        Vector<Double> expected = new Vector<>(List.of(1.,0.,0., -1.));
        // X = 0*Y + 0*Z + 1
        assertEquals(expected, actual);
    }

    @Test
    void normalize() {
        //Given
        Vector<Double> vec = new Vector<Double>(List.of(3., 4.));
        //When
        Vector<Double> actual = painter.normalize(vec);

        //Then
        Vector<Double> expected = new Vector<>(List.of(0.6,0.8));
        assertEquals(expected, actual);
    }

    @Test
    void valProduct() {
        //Given
        Vector<Double> vec = new Vector<>(List.of(3.,4.));

        //When
        Vector<Double> actual = painter.valProduct(vec, 2.0);

        //Then
        Vector<Double> expected = new Vector<>(List.of(6.,8.));

        assertEquals(expected, actual);
    }

    @Test
    void movePointByVector() {
        //Given
        Vector<Double> vec = new Vector<>(List.of(3.,-4.,5.));

        Point point = new Point(1., 2.,6.);

        //When
        Point actual = painter.movePointByVector(point, vec);

        //Then
        Point expected = new Point(4., -2., 11.);

        assertEquals(expected, actual);
    }

    @Test
    void getD() {

    }
}