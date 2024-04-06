package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


public class Main extends Application {
    public static void main(String []args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene s = new Scene(root);
        Camera camera = new Camera();
        setUpKeyHandler(s, (moveType) -> camera.move(moveType));
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);
        primaryStage.setScene(s);
        primaryStage.show();

        File file = new File("src/main/java/org/example/figures.txt");
        System.out.println(file.getAbsolutePath());
        List<Figure> figures = readFigure(file);

        /*
        frame.addKeyListener(new KeyPressListener(move -> {
            makeAMove(camera, frame, move);
            draw(camera, figures, frame);
            return null;
        }));
        */

    }

    private static void setUpKeyHandler(Scene s, Function<TypeOfMoves,Void> callback){
        s.setOnKeyPressed(new KeyPressListener(callback));
    }

    public static void draw(Camera camera, List<Figure> figures, Frame frame){
        Painter painter = new Painter();
        frame.removeAll();
        for (Figure figure : figures)
            for (Edge edge : figure.getEdges()) {
                for (Point point : edge.getPoints())
                    frame.paintObject(painter.getPaintingOfPoint(camera, point));
                frame.paintObject(painter.getPaintingOfEdge(camera, edge));
            }
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