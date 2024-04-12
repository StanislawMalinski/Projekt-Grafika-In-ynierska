package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.dataTypes.Figure;
import org.example.enums.TypeOfMoves;
import org.example.graphics.Camera;
import org.example.graphics.Painter;
import org.example.nodes.Edge;
import org.example.nodes.Point;
import org.example.utils.KeyPressListener;
import org.example.utils.VectorCalculator;
import org.example.utils.ResizeTool;
import org.example.utils.SceneReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.atan;


public class Main extends Application {
    private Camera camera;
    private Scene scene;
    private Group root;
    private Painter painter;
    private ResizeTool resizeTool;
    private List<Figure> figures;

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    
    public static void main(String []args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new Group();
        scene = new Scene(root);
        camera = new Camera();
        painter = new Painter(camera);
        resizeTool = new ResizeTool(WIDTH, HEIGHT);

        File file = new File("src/main/resources/figures.txt");
        figures = SceneReader.readFigure(file);
        painter.setUpNewCanvas();
        draw();

        setUpKeyHandler(scene);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpKeyHandler(Scene s){
        s.setOnKeyPressed(new KeyPressListener(this::callBack));
    }

    private Void callBack(TypeOfMoves typeOfMoves) {
        camera.move(typeOfMoves);
        draw();
        return null;
    }

    public void draw(){
        painter.setUpNewCanvas();
        root.getChildren().clear();
        List<Node> objects = new ArrayList<>();
        Point n;
        for (Figure figure : figures)
            for (Edge edge : figure.getEdges()) {
                for (Point point : edge.getPoints()) {
                    n = painter.getPaintingOfPoint(camera, point);
                    System.out.println("Before mapping: " + n);
                    resizeTool.resize(n);
                    System.out.println("After mapping: " + n);
                    objects.add(n);
                }
                objects.add(painter.getPaintingOfEdge(camera, edge));
            }
        objects.forEach(node -> root.getChildren().add(node));
    }
}