package org.example.utils;

import org.example.dataTypes.Quaternion;
import org.example.nodes.Point;

import java.util.List;
import java.util.Vector;

public class VectorCalculator {
    public static Vector<Double> normalize(Vector<Double> vector) {
        double length = 0;
        for (Double aDouble : vector) {
            length += aDouble * aDouble;
        }
        length = Math.sqrt(length);
        Vector<Double> result = new Vector<>();
        for (Double aDouble : vector) {
            result.add(aDouble / length);
        }
        return result;
    }

    public static Vector<Double> valProduct(Vector<Double> vector, double value) {
        Vector<Double> result = new Vector<>();
        for (Double aDouble : vector) {
            result.add(aDouble * value);
        }
        return result;
    }

    public static Vector<Double> calculateVecFromAtoB(Point A, Point B) {
        Vector<Double> result = new Vector<>();
        result.add(B.getX() - A.getX());
        result.add(B.getY() - A.getY());
        result.add(B.getZ() - A.getZ());
        return result;
    }

    public static double dotProduct(Vector<Double> a, Vector<Double> b) {
        double result = 0;
        for (int i = 0; i < a.size(); i++) {
            result += a.get(i) * b.get(i);
        }
        return result;
    }

    public static Vector<Double> crossProduct(Vector<Double> a, Vector<Double> b) {
        Vector<Double> result = new Vector<>();
        result.add(a.get(1) * b.get(2) - a.get(2) * b.get(1));
        result.add(a.get(2) * b.get(0) - a.get(0) * b.get(2));
        result.add(a.get(0) * b.get(1) - a.get(1) * b.get(0));
        return result;
    }

    public static double angleBetweenVectors(Vector<Double> a, Vector<Double> b) {
        return Math.acos(dotProduct(a, b) / (vectorLength(a) * vectorLength(b)));
    }

    public static double vectorLength(Vector<Double> vector) {
        double length = 0;
        for (Double aDouble : vector) {
            length += aDouble * aDouble;
        }
        return Math.sqrt(length);
    }

    public static Point movePointByVector(Point point, Vector<Double> vector) {
        return new Point(point.getX() + vector.get(0), point.getY() + vector.get(1), point.getZ() + vector.get(2));
    }

    public static Vector<Double> rotateVectorByQuaternion(Quaternion q, Vector<Double> v) {
        Quaternion p = new Quaternion(0, v.get(0), v.get(1), v.get(2));
        Quaternion n = rotateQuaternionByQuaternion(q, p);
        return getVectorFromQuaternion(n);
    }

    public static Quaternion rotateQuaternionByQuaternion(Quaternion rotation, Quaternion toBeRotated) {
        return  rotation.multiply(toBeRotated).multiply(rotation.inverse()).normalize();
    }

    public static Point rotatePointByQuaternion(Quaternion q, Point p) {
        Quaternion n = new Quaternion(0, p.getX(), p.getY(), p.getZ());
        Quaternion qnq = rotateQuaternionByQuaternion(q, n);
        Vector<Double> v =  getVectorFromQuaternion(qnq);
        return getPointFromVector(v);
    }

    public static Vector<Double> getVectorFromPoint(Point a){
        return new Vector<>(List.of(a.getX(), a.getY(), a.getZ()));
    }

    public static Point getPointFromVector(Vector<Double> a){
        return new Point(a.get(0), a.get(1), a.get(2));
    }

    public static Vector<Double> conjugateVector(Vector<Double> a){
        return new Vector<>(List.of(-a.get(0), -a.get(1), -a.get(2)));
    }

    public static Vector<Double> getVectorFromQuaternion(Quaternion q){
        return new Vector<>(List.of(q.getX1(), q.getX2(), q.getX3()));
    }
}
