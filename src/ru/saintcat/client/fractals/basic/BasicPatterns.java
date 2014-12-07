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
import ru.saintcat.client.fractals.LineOperations;
import ru.saintcat.client.fractals.MatrixOperations;

/**
 *
 * @author Chernyshov
 */
public class BasicPatterns {

    public static List<Line> getKohaLinePattern() {
        List<Line> lines = new ArrayList<>();
        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(1, 0);

        double Vx = (b.getX() - a.getX()) / 3;
        double Vy = (b.getY() - a.getY()) / 3;

        Point2D c = new Point2D(a.getX() + Vx, a.getY() + Vy);
        Point2D d = new Point2D(c.getX() + Vx, c.getY() + Vy);
        double[][] res = MatrixOperations.multiply(new double[][]{{Vx, Vy}}, MatrixOperations.createRotationMatrix(Math.PI / 3));
        Point2D e = new Point2D(c.getX() + res[0][0], c.getY() + res[0][1]);

        Line ac = new Line(a.getX(), a.getY(), c.getX(), c.getY());
        Line ce = new Line(c.getX(), c.getY(), e.getX(), e.getY());
        Line ed = new Line(e.getX(), e.getY(), d.getX(), d.getY());
        Line db = new Line(d.getX(), d.getY(), b.getX(), b.getY());
        lines.add(ac);
        lines.add(ce);
        lines.add(ed);
        lines.add(db);
        return lines;
    }

    public static List<Line> getGosperLine6Pattern() {
        List<Line> lines = new ArrayList<>();

        double r6 = Math.pow((5 + 4 * Math.cos(2 * Math.PI / 6)), -0.5);
        System.out.println(r6);
        double angle = Math.PI - 2 * Math.PI / 6;
        System.out.println(angle / Math.PI * 180);

        double alpha = Math.PI - angle - Math.asin(r6 / 0.5 * Math.sin(angle));
        System.out.println(alpha / Math.PI * 180);

//        double[][] res = MatrixOperations.multiply(new double[][]{{r6, 0}}, MatrixOperations.createRotationMatrix(alpha));
        double[][] res = MatrixOperations.rotatePoint(r6, 0, 0, 0, -alpha);
        Point2D f = new Point2D(res[0][0], res[0][1]);

        res = MatrixOperations.rotatePoint(1 - r6, 0, 1, 0, -alpha);
        Point2D f2 = new Point2D(res[0][0], res[0][1]);

        lines.add(new Line(0, 0, f.getX(), f.getY()));
        lines.add(new Line(f.getX(), f.getY(), f2.getX(), f2.getY()));
        lines.add(new Line(f2.getX(), f2.getY(), 1, 0));
        return lines;
    }

    public static List<Line> getGosperLine4Pattern() {
        List<Line> lines = new ArrayList<>();
   double r6 = Math.pow((5 + 4 * Math.cos(2 * Math.PI / 4)), -0.5);
        System.out.println(r6);
        double angle = Math.PI - 2 * Math.PI / 4;
        System.out.println(angle / Math.PI * 180);

        double alpha = Math.PI - angle - Math.asin(r6 / 0.5 * Math.sin(angle));
        System.out.println(alpha / Math.PI * 180);

//        double[][] res = MatrixOperations.multiply(new double[][]{{r6, 0}}, MatrixOperations.createRotationMatrix(alpha));
        double[][] res = MatrixOperations.rotatePoint(r6, 0, 0, 0, -alpha);
        Point2D f = new Point2D(res[0][0], res[0][1]);

        res = MatrixOperations.rotatePoint(1 - r6, 0, 1, 0, -alpha);
        Point2D f2 = new Point2D(res[0][0], res[0][1]);

        lines.add(new Line(0, 0, f.getX(), f.getY()));
        lines.add(new Line(f.getX(), f.getY(), f2.getX(), f2.getY()));
        lines.add(new Line(f2.getX(), f2.getY(), 1, 0));
        return lines;
    }

    public static List<Line> getGosperLine3Pattern() {
        List<Line> lines = new ArrayList<>();
   double r6 = Math.pow((5 + 4 * Math.cos(2 * Math.PI / 3)), -0.5);
        System.out.println(r6);
        double angle = Math.PI - 2 * Math.PI / 3;
        System.out.println(angle / Math.PI * 180);

        double alpha = Math.PI - angle - Math.asin(r6 / 0.5 * Math.sin(angle));
        System.out.println(alpha / Math.PI * 180);

//        double[][] res = MatrixOperations.multiply(new double[][]{{r6, 0}}, MatrixOperations.createRotationMatrix(alpha));
        double[][] res = MatrixOperations.rotatePoint(r6, 0, 0, 0, -alpha);
        Point2D f = new Point2D(res[0][0], res[0][1]);

        res = MatrixOperations.rotatePoint(1 - r6, 0, 1, 0, -alpha);
        Point2D f2 = new Point2D(res[0][0], res[0][1]);

        lines.add(new Line(0, 0, f.getX(), f.getY()));
        lines.add(new Line(f.getX(), f.getY(), f2.getX(), f2.getY()));
        lines.add(new Line(f2.getX(), f2.getY(), 1, 0));
        return lines;
    }

    public static List<Line> getMandelbrotGivenPattern() {
        List<Line> lines = new ArrayList<>();

        lines.add(new Line(0, 0, (double) 1 / 3, 0));
        lines.add(new Line((double) 1 / 3, 0, (double) 2 / 3, 0));
        lines.add(new Line((double) 2 / 3, 0, 1, 0));

        lines.add(new Line((double) 1 / 3, 0, (double) 1 / 3, (double) 1 / 3));
        lines.add(new Line((double) 1 / 3, (double) 1 / 3, (double) 2 / 3, (double) 1 / 3));
        lines.add(new Line((double) 2 / 3, (double) 1 / 3, (double) 2 / 3, 0));

        return lines;
    }

    public static List<Line> getMandelbrotGiven2Pattern() {
        List<Line> lines = new ArrayList<>();
        Line aaa1 = new Line(0, 0.5, 0.5, 0.5);
        Line aaa2 = new Line(0.5, 0.5, 0.5, 0);
        Line aaa3 = new Line(0.5, 0, 0.5, -0.5);
        Line aaa4 = new Line(0.5, -0.5, 1, -0.5);
        lines.add(aaa1);
        lines.add(aaa2);
        lines.add(aaa3);
        lines.add(aaa4);
        return lines;
    }

    public static List<Line> getPeanoLinePattern() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(0, 0, 0.5, 0.5));
        lines.add(new Line(0.5, 0.5, 1, 0));
        return lines;
    }

    public static List<Line> getPeanoLine2Pattern() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(0, 0, 0, 0.5));
        lines.add(new Line(0, 0.5, 0.5, 0.5));
        lines.add(new Line(0.5, 0.5, 1, 0.5));
        lines.add(new Line(1, 0.5, 1, 0));
        return lines;
    }

    public static List<Line> getPeanoLine3Pattern() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(0, 0, 0.5, 0));
        lines.add(new Line(0.5, 0, 0.5, 0.5));
        lines.add(new Line(0.5, 0.5, 0.5, 0));
        lines.add(new Line(0.5, 0, 1, 0));
        return lines;
    }

    public static List<Line> getPeanoLine4Pattern() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(0, 0, (double) 1 / 3, 0));
        lines.add(new Line((double) 1 / 3, 0, (double) 1 / 3, (double) 1 / 3));
        lines.add(new Line((double) 1 / 3, (double) 1 / 3, (double) 2 / 3, (double) 1 / 3));
        lines.add(new Line((double) 2 / 3, (double) 1 / 3, (double) 2 / 3, 0));
        lines.add(new Line((double) 2 / 3, 0, (double) 1 / 3, 0));
        lines.add(new Line((double) 1 / 3, 0, (double) 1 / 3, (double) -1 / 3));
        lines.add(new Line((double) 1 / 3, (double) -1 / 3, (double) 2 / 3, (double) -1 / 3));
        lines.add(new Line((double) 2 / 3, (double) -1 / 3, (double) 2 / 3, 0));
        lines.add(new Line((double) 2 / 3, 0, 1, 0));
        return lines;
    }

}
