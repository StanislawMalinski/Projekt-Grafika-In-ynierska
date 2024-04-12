package org.example.nodes;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Edge extends Line {
    private List<Point> points;
    public Edge(){
        points = new ArrayList<>();
    }

    public Edge(Object[] points) {
        if (points.length != 2) {
            throw new IllegalArgumentException("An edge must have exactly two points");
        }
        if (points[0] == null || points[1] == null) {
            throw new IllegalArgumentException("An edge must have two non-null points");
        }
        if (points[0].getClass() == Point.class) {
            this.points = List.of((Point) points[0], (Point) points[1]);
        } else if (points[0].getClass() == String.class) {
            this.points = List.of(new Point((String) points[0]), new Point((String) points[1]));
        } else {
            throw new IllegalArgumentException("An edge must have two points of type Point");
        }

        setStartX(this.points.get(0).getX());
        setStartY(this.points.get(0).getY());
        setEndX(this.points.get(1).getX());
        setEndY(this.points.get(1).getY());
        setVisible(true);
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return Objects.equals(points.get(0), edge.points.get(0)) && Objects.equals(points.get(1), edge.points.get(1)) ||
                Objects.equals(points.get(0), edge.points.get(1)) && Objects.equals(points.get(1), edge.points.get(0));
    }
}
