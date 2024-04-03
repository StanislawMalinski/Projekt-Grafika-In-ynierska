package org.example;

import java.util.Vector;

import static java.lang.Math.tan;

public class Painter {
    private final double distanceToCanvas = 1;

    public void paint(Camera camera, Point point, Frame frame) {
        Vector<Double> line = calculateLine(camera.getPosition(), point);
        Vector<Double> canvasFormulaFactors = calculateCanvas(camera);
        Point intersection = calculateIntersection(line, point, canvasFormulaFactors);
        frame.paintPoint(intersection.getX(), intersection.getY());
    }

    /*
    * Line representation:
    *   { x = x1 + t*a
    *   { y = y1 + t*b
    *   { z = z1 + t*c
    *
    * Plane representation:
    *  { A*x + B*y + C*z + D = 0
    *
    * Intersection point:
    *  A * (x1 + t*a) + B * (y1 + t*b) + C * (z1 + t*c) + D = 0
    *  A * x1 + B * y1 + C * z1 + D = - A * t*a - B * t*b - C * t*c
    *  - (A * x1 + B * y1 + C * z1 + D) / ( A * a + B * b + C * c) = t
    *
    * */


    private boolean isVisible(Point point, Camera cam) {
        double deltaY = distanceToCanvas / tan(cam.getZoom()/2);
        double deltaX = distanceToCanvas / tan(cam.getZoom()/2);
    }

    private Point calculateIntersection(Vector<Double> line, Point point, Vector<Double> canvasFormulaFactors) {
        double t = - (canvasFormulaFactors.get(0) * point.getX() + canvasFormulaFactors.get(1) * point.getY() + canvasFormulaFactors.get(2) * point.getZ())
                / (canvasFormulaFactors.get(0) * line.get(0) + canvasFormulaFactors.get(1) * line.get(1) + canvasFormulaFactors.get(2) * line.get(2));
        double x = point.getX() + t * line.get(0);
        double y = point.getY() + t * line.get(1);
        System.out.println(x + " " + y);
        return new Point(x, y, distanceToCanvas);
    }

    private Vector<Double> calculateLine(Point point1, Point point2){
        Vector<Double> lineVec = new Vector<>();
        lineVec.add(point2.getX() - point1.getX());
        lineVec.add(point2.getY() - point1.getY());
        lineVec.add(point2.getZ() - point1.getZ());
        return lineVec;
    }
    private Vector<Double> calculateCanvas(Camera camera){
        Vector<Double> canvasNormalVec = new Vector<>();
        canvasNormalVec.add(camera.getOrientation().getX1());
        canvasNormalVec.add(camera.getOrientation().getX2());
        canvasNormalVec.add(camera.getOrientation().getX3());
        canvasNormalVec = valProduct(normalize(canvasNormalVec), distanceToCanvas);

        Point canvasPivot = movePointByVector(camera.getPosition(), canvasNormalVec);
        Vector<Double> canvasFormulaFactors = new Vector<>();
        canvasFormulaFactors.add(canvasNormalVec.get(0));
        canvasFormulaFactors.add(canvasNormalVec.get(1));
        canvasFormulaFactors.add(canvasNormalVec.get(2));
        canvasFormulaFactors.add(getD(canvasPivot, canvasNormalVec));
        return canvasFormulaFactors;
    }

    private Vector<Double> normalize(Vector<Double> vec) {
        double norm = 0;
        for (double value : vec) {
            norm += value * value;
        }
        double length = Math.sqrt(norm);
        Vector<Double> normalizedVec = new Vector<>();
        for (double value : vec) {
            normalizedVec.add(value / length);
        }
        return normalizedVec;
    }

    private Vector<Double> valProduct(Vector<Double> vec, double val) {
        Vector<Double> result = new Vector<>();
        for (double value : vec) {
            result.add(value * val);
        }
        return result;
    }

    private Point movePointByVector(Point point, Vector<Double> vec) {
        return new Point(point.getX() + vec.get(0), point.getY() + vec.get(1), point.getZ() + vec.get(2));
    }

    private double getD(Point point, Vector<Double> normalVec) {
        return -normalVec.get(0) * point.getX() - normalVec.get(1) * point.getY() - normalVec.get(2) * point.getZ();
    }
}
