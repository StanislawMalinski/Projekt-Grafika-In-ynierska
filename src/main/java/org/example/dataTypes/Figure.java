package org.example.dataTypes;

import org.example.nodes.Edge;
import org.example.nodes.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.utils.VectorCalculator.movePointByVector;

public class Figure {
    private List<Edge> edges;
    private Set<Edge> edgesset;
    private Point pivot;
    private static final Pattern pattern = Pattern.compile("\\((\\d+\\.?\\d*),\\s*(\\d+\\.?\\d*),\\s*(\\d+\\.?\\d*)\\)");
    private Matcher matcher;

    public Figure(Point pivot, List<Edge> edges) {
        setUp(pivot, edges);
    }

    public Figure(String string){
        String[] parts = string.split("&");
        List<Edge> edges = new ArrayList<>();
        Point pivot = new Point(parts[0]), n;
        Edge e;
        Vector<Double> rea;
        for (int i = 1; i < parts.length; i++) {
            e = new Edge();
            for (String s : parts[i].split("-")){
                rea = getVectorFromString(s);
                n = movePointByVector(pivot.copy(), rea);
                e.getPoints().add(n);
            }
            edges.add(e);
        }
        setUp(pivot, edges);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void setUp(Point pivot, List<Edge> edges) {
        this.edges = edges;
        this.edgesset = Set.copyOf(edges);
        this.pivot = pivot;
    }

    private Vector<Double> getVectorFromString(String string){
        matcher = pattern.matcher(string);
        if (matcher.find()){
            double x = Double.parseDouble(matcher.group(1));
            double y = Double.parseDouble(matcher.group(2));
            double z = Double.parseDouble(matcher.group(3));
            return new Vector<>(List.of(x, y, z));
        }
        throw new RuntimeException("Invalid string format");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        if (!pivot.equals(figure.pivot)) return false;
        for (Edge edge : edges) {
            if (!figure.edgesset.contains(edge)) return false;
        }
        return true;
    }
}
