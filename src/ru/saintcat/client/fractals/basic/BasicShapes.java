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
import ru.saintcat.client.fractals.MatrixOperations;

/**
 *
 * @author Chernyshov
 */
public class BasicShapes {

    public static List<Line> getLineShape(Point2D center, double lenght) {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(center.getX() - lenght / 2, center.getY(), center.getX() + lenght / 2, center.getY()));
        return lines;
    }

    public static List<Line> getTriangleShape(Point2D center, double radius) {
        List<Line> lines = new ArrayList<>();

        double aX = center.getX();
        double aY = center.getY() - radius;

        double[][] res = MatrixOperations.rotatePoint(aX, aY, center.getX(), center.getY(), 120 * Math.PI / 180);
        double bX = res[0][0];
        double bY = res[0][1];
        res = MatrixOperations.rotatePoint(bX, bY, center.getX(), center.getY(), 120 * Math.PI / 180);
        double cX = res[0][0];
        double cY = res[0][1];

        lines.add(new Line(aX, aY, bX, bY));
        lines.add(new Line(bX, bY, cX, cY));
        lines.add(new Line(cX, cY, aX, aY));
        return lines;
    }

    public static List<Line> getSquareShape(Point2D center, double width, double height) {
        List<Line> lines = new ArrayList<>();
        double aX = center.getX() - width / 2;
        double aY = center.getY() - height / 2;

        double bX = center.getX() + width / 2;
        double bY = center.getY() - height / 2;

        double cX = center.getX() + width / 2;
        double cY = center.getY() + height / 2;

        double dX = center.getX() - width / 2;
        double dY = center.getY() + height / 2;
        lines.add(new Line(aX, aY, bX, bY));
        lines.add(new Line(bX, bY, cX, cY));
        lines.add(new Line(cX, cY, dX, dY));
        lines.add(new Line(dX, dY, aX, aY));

        return lines;
    }

    public static List<Line> getHexagonShape(Point2D center, double radius) {
        List<Line> lines = new ArrayList<>();
        double aX = center.getX();
        double aY = center.getY() - radius;
        double bX = 0;
        double bY = 0;
        for (int i = 0; i < 6; i++) {
            double[][] res = MatrixOperations.rotatePoint(aX, aY, center.getX(), center.getY(), 60 * Math.PI / 180);
            bX = res[0][0];
            bY = res[0][1];
            lines.add(new Line(aX, aY, bX, bY));
            aX = bX;
            aY = bY;
        }
        return lines;
    }
}
