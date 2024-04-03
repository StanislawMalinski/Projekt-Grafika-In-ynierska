package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Figure {
    private List<Edge> edges;
    private Set<Edge> edgesset;
    private Point pivot;

    public Figure(Point pivot, List<Edge> edges) {
        setUp(pivot, edges);
    }

    public Figure(String string){
        String[] parts = string.split("&");
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            edges.add(new Edge(parts[i].split("-")));
        }
        Point pivot = new Point(parts[0]);
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
