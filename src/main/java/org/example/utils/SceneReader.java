package org.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.dataTypes.Figure;

public class SceneReader {
   public static List<Figure> readFigure(File file){
    List<Figure> figures = new ArrayList<>();
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
