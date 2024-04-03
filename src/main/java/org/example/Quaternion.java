package org.example;

public class Quaternion {
    private final double x0, x1, x2, x3;

    public Quaternion(double x0, double x1, double x2, double x3) {
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
    }

    public String toString() {
        return x0 + " + " + x1 + "i + " + x2 + "j + " + x3 + "k";
    }

    public double norm() {
        return Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2 + x3 * x3);
    }

    public Quaternion conjugate() {
        return new Quaternion(x0, -x1, -x2, -x3);
    }
    public Quaternion plus(Quaternion b) {
        Quaternion a = this;
        return new Quaternion(a.x0 + b.x0, a.x1 + b.x1, a.x2 + b.x2, a.x3 + b.x3);
    }

    public Quaternion multiply(double alpha) {
        return new Quaternion(alpha * x0, alpha * x1, alpha * x2, alpha * x3);
    }
    public Quaternion multiply(Quaternion a){
        return new Quaternion(
                x0 * a.x0 - x1 * a.x1 - x2 * a.x2 - x3 * a.x3,
                x0 * a.x1 + x1 * a.x0 + x2 * a.x3 - x3 * a.x2,
                x0 * a.x2 - x1 * a.x3 + x2 * a.x0 + x3 * a.x1,
                x0 * a.x3 + x1 * a.x2 - x2 * a.x1 + x3 * a.x0
        );
    }

    public Quaternion times(Quaternion b) {
        Quaternion a = this;
        double y0 = a.x0 * b.x0 - a.x1 * b.x1 - a.x2 * b.x2 - a.x3 * b.x3;
        double y1 = a.x0 * b.x1 + a.x1 * b.x0 + a.x2 * b.x3 - a.x3 * b.x2;
        double y2 = a.x0 * b.x2 - a.x1 * b.x3 + a.x2 * b.x0 + a.x3 * b.x1;
        double y3 = a.x0 * b.x3 + a.x1 * b.x2 - a.x2 * b.x1 + a.x3 * b.x0;
        return new Quaternion(y0, y1, y2, y3);
    }

    public Quaternion inverse() {
        double d = x0 * x0 + x1 * x1 + x2 * x2 + x3 * x3;
        return new Quaternion(x0 / d, -x1 / d, -x2 / d, -x3 / d);
    }

    public Quaternion divides(Quaternion b) {
        Quaternion a = this;
        return a.times(b.inverse());
    }

    public double getX0() {
        return x0;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

}
