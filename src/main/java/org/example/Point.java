package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point{
    private double x;
    private double y;
    private double z;
    private static final Pattern pattern = Pattern.compile("\\((\\d+\\.?\\d*),\\s*(\\d+\\.?\\d*),\\s*(\\d+\\.?\\d*)\\)");
    private Matcher matcher;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(String string){
        matcher = pattern.matcher(string);
        if (matcher.find()){
            x = Double.parseDouble(matcher.group(1));
            y = Double.parseDouble(matcher.group(2));
            z = Double.parseDouble(matcher.group(3));
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        if (Double.compare(point.y, y) != 0) return false;
        return Double.compare(point.z, z) == 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
