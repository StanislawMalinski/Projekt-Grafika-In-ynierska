package org.example.graphics;

import org.example.nodes.Edge;
import org.example.nodes.Point;

import java.util.Vector;

import static java.lang.Math.tan;
import static org.example.utils.VectorCalculator.*;

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
 */

public class Painter {
    protected static final double distanceToCanvas = 10;
    private Vector<Double> canvas;
    private Camera camera;

    public Painter(Camera camera) {
        this.camera = camera;
        this.canvas = calculateCanvas(camera.getPosition(), camera.getOrientationVector());
    }

    public boolean isVisible(Vector<Double> vec1, Vector<Double> vec2, double angle) {
        return angle > angleBetweenVectors(vec1, vec2);
    }

    public Point normalizePointLayout(Point point) {
        double maxX = tan(camera.getZoomX() / 2) * distanceToCanvas * 2;
        double maxY = tan(camera.getZoomY() / 2) * distanceToCanvas * 2;
        point.setX(point.getX() / maxX);
        point.setY(point.getY() / maxY);
        System.out.println("maxX: " + maxX + " maxY: " + maxY);
        return point;
    }

    public void setUpNewCanvas() {
        this.canvas = calculateCanvas(camera.getPosition(), camera.getOrientationVector());
    }

    public Point getPaintingOfPoint(Camera camera, Point point) {
        Point p = calculatePoint2d(camera.getPosition(), point, canvas);
        System.out.println(p);
        return p;
    }

    private Point mapByZAxis(Point p, Camera camera) {
        Vector<Double> toCamera = getVectorFromPoint(camera.getPosition());
        Vector<Double> toOrigin = conjugateVector(toCamera);
        Point newPPosition = movePointByVector(p, toOrigin);
        newPPosition = rotatePointByQuaternion(camera.getOrientation(), newPPosition);
        return movePointByVector(newPPosition, toCamera);
    }

    public Edge getPaintingOfEdge(Camera camera, Edge edge) {
        return calculateEdge2d(camera.getPosition(), edge, canvas);
    }

    protected Point calculatePoint2d(Point A, Point B, Vector<Double> canvas) {
        Vector<Double> line = calculateVecFromAtoB(A, B);
        Point p = calculateIntersection(line, A, canvas);
        p = mapByZAxis(p, camera);
        return normalizePointLayout(p);
    }

    protected Edge calculateEdge2d(Point A, Edge B, Vector<Double> canvas) {
        Point p1 = calculatePoint2d(A, B.getPoints().get(0), canvas);
        Point p2 = calculatePoint2d(A, B.getPoints().get(1), canvas);
        return new Edge(new Point[] {p1, p2});
    }

    protected Point calculateIntersection(Vector<Double> line, Point point, Vector<Double> canvasFormulaFactors) {
        double t = - (canvasFormulaFactors.get(0) * point.getX() + canvasFormulaFactors.get(1) * point.getY() + canvasFormulaFactors.get(2) * point.getZ() + canvasFormulaFactors.get(3))
                / (canvasFormulaFactors.get(0) * line.get(0) + canvasFormulaFactors.get(1) * line.get(1) + canvasFormulaFactors.get(2) * line.get(2));
        double x = point.getX() + t * line.get(0);
        double y = point.getY() + t * line.get(1);
        double z = point.getZ() + t * line.get(2);
        return new Point(x, y, z);
    }

    protected Vector<Double> calculateCanvas(Point position, Vector<Double> orientation){
        Vector<Double> canvasNormalVec = new Vector<>();
        canvasNormalVec.add(orientation.get(0));
        canvasNormalVec.add(orientation.get(1));
        canvasNormalVec.add(orientation.get(2));
        canvasNormalVec = valProduct(normalize(canvasNormalVec), distanceToCanvas);
        Point canvasPivot = movePointByVector(position, canvasNormalVec);

        canvasNormalVec.add(getD(canvasPivot, canvasNormalVec));
        return canvasNormalVec;
    }

    protected double getD(Point point, Vector<Double> vec) {
        return  (vec.get(0) * point.getX() + vec.get(1) * point.getY() + vec.get(2) * point.getZ());
    }
}
