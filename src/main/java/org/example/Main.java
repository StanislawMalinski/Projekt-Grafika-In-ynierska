package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        List<Figure> figures = readFigure(file);
        Frame frame = new Frame();
        Camera camera = new Camera();
        frame.addKeyListener(new KeyPressListener(move -> {
            makeAMove(camera, frame, move);
            draw(camera, figures, frame);
            return null;
        }));
    }

    public static void makeAMove(Camera camera, Frame frame,TypeOfMoves move) {
        camera.move(move);
    }

    public static void draw(Camera camera, List<Figure> figures, Frame frame){
        Painter painter = new Painter();
        frame.paintPoint(100, 100);
        for (Figure figure : figures)
            for (Edge edge : figure.getEdges())
                for (Point point : edge.getPoints())
                    painter.paint(camera, point, frame);
        frame.update(frame.getGraphics());
    }
    public static List<Figure> readFigure(File file){
        List<Figure> figures = new ArrayList<Figure>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                figures.add(new Figure(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return figures;
    }
}