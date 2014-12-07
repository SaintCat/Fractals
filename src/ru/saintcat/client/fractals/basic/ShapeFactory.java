/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals.basic;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

/**
 *
 * @author Chernyshov
 */
public class ShapeFactory {

    public static List<Line> getLines(ShapeType shapeName) {
        switch (shapeName) {
            case LINE:
                return BasicShapes.getLineShape(new Point2D(300, 300), 400);
            case SQUARE:
                return BasicShapes.getSquareShape(new Point2D(350, 300), 240, 240);
            case TRIANGLE:
                return BasicShapes.getTriangleShape(new Point2D(350, 300), 200);
            case HEXAGON:
                return BasicShapes.getHexagonShape(new Point2D(350, 300), 170);
            default:
                return new ArrayList<>();
        }
    }
}
