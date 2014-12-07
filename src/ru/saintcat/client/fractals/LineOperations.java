/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals;

import javafx.scene.shape.Line;

/**
 *
 * @author Chernyshov
 */
public class LineOperations {

    public static Line transferLine(Line line, double dX, double dY) {
        Line l = new Line();
        l.setStartX(line.getStartX() + dX);
        l.setStartY(line.getStartY() + dY);
        l.setEndX(line.getEndX() + dX);
        l.setEndY(line.getEndY() + dY);
        return l;
    }

    public static double[][] getLinePointsArray(Line line) {
        return new double[][]{{line.getStartX(), line.getStartY()}, {line.getEndX(), line.getEndY()}};
    }

    public static double lineLenght(Line line) {
        return Math.sqrt(Math.pow(line.getEndX() - line.getStartX(), 2) + Math.pow(line.getEndY() - line.getStartY(), 2));
    }
}
