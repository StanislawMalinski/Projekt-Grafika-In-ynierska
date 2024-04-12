package org.example.utils;

import org.example.nodes.Point;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class VectorCalculatorTest {

    @Test
    void normalize() {
        // given
        Vector<Double> vector = new Vector<>(List.of(1., 2., 3.));
        Vector<Double> expected = new Vector<>(List.of(0.2672612419124244, 0.5345224838248488, 0.8017837257372732));
        // when
        Vector<Double> result = VectorCalculator.normalize(vector);
        // then
        assertEquals(expected, result);
    }

    @Test
    void valProduct() {
        // given
        Vector<Double> vector = new Vector<>(List.of(1., 2., 3.));
        double value = 2;
        Vector<Double> expected = new Vector<>(List.of(2., 4., 6.));
        // when
        Vector<Double> result = VectorCalculator.valProduct(vector, value);
        // then
        assertEquals(expected, result);
    }

    @Test
    void calculateVecFromAtoB() {
        // given
        Vector<Double> expected = new Vector<>(List.of(3., 4., 5.));
        Point A = new Point(1, 2, 3);
        Point B = new Point(4, 6, 8);
        // when
        Vector<Double> result = VectorCalculator.calculateVecFromAtoB(A, B);
        // then
        assertEquals(expected, result);
    }

    @Test
    void dotProduct() {
        // given
        Vector<Double> a = new Vector<>(List.of(1., 2., 3.));
        Vector<Double> b = new Vector<>(List.of(4., 5., 6.));
        double expected = 32;
        // when
        double result = VectorCalculator.dotProduct(a, b);
        // then
        assertEquals(expected, result);
    }

    @Test
    void crossProduct() {
        // given
        Vector<Double> a = new Vector<>(List.of(1., 2., 3.));
        Vector<Double> b = new Vector<>(List.of(4., 5., 6.));
        Vector<Double> expected = new Vector<>(List.of(-3., 6., -3.));
        // when
        Vector<Double> result = VectorCalculator.crossProduct(a, b);
        // then
        assertEquals(expected, result);
    }

    @Test
    void angleBetweenVectors() {
        // given
        Vector<Double> a = new Vector<>(List.of(1., 2., 3.));
        Vector<Double> b = new Vector<>(List.of(4., 5., 6.));
        double expected = 0.2257261285527342;
        // when
        double result = VectorCalculator.angleBetweenVectors(a, b);
        // then
        assertEquals(expected, result);
    }

    @Test
    void angleBetweenVectorsEdgeCase() {
        // given
        Vector<Double> a = new Vector<>(List.of(0.00000001, 0., 0.));
        Vector<Double> b = new Vector<>(List.of(0.00000001, 0., 0.));
        double expected = 0.0;
        // when
        double result = VectorCalculator.angleBetweenVectors(a, b);
        // then
        assertEquals(expected, result);
    }

    @Test
    void vectorLength() {
        // given
        Vector<Double> vector = new Vector<>(List.of(1., 2., 3.));
        double expected = 3.7416573867739413;
        // when
        double result = VectorCalculator.vectorLength(vector);
        // then
        assertEquals(expected, result);
    }

    @Test
    void movePointByVector() {
        // given
        Point point = new Point(1, 2, 3);
        Vector<Double> vector = new Vector<>(List.of(4., 5., 6.));
        Point expected = new Point(5, 7, 9);
        // when
        Point result = VectorCalculator.movePointByVector(point, vector);
        // then
        assertEquals(expected, result);
    }
}