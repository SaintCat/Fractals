/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import static ru.saintcat.client.fractals.FractalController.arg;
import static ru.saintcat.client.fractals.LineOperations.getLinePointsArray;
import static ru.saintcat.client.fractals.LineOperations.lineLenght;
import static ru.saintcat.client.fractals.LineOperations.transferLine;

/**
 *
 * @author Chernyshov
 */
public class FractalBuilder {

    public static List<Line> teragonIter(List<Line> o, List<Line> s,
            double delta, int it, int D, int Di, int Do) {

        for (int i = 0; i < it; i++) {
            List<Line> Ls = new ArrayList<>();
            boolean flag = false;
            for (int k = 0; k < o.size(); k++) {
                Line ab = o.get(k);
                double r = lineLenght(ab);
                if (!(r < delta)) {
                    double fi = arg(ab.getEndX() - ab.getStartX(), ab.getEndY() - ab.getStartY());
                    boolean v = false;
                    if ((Di == 1 && i % 2 == 0) || (Di == -1 && i % 2 != 0)) {
                        v = !v;
                    }
                    if ((Do == 1 && k % 2 == 0) || (Do == -1 && k % 2 != 0)) {
                        v = !v;
                    }
                    for (int j = 0; j < s.size(); j++) {
                        Line sh = s.get(j);
                        double[][] first = MatrixOperations.multiply(getLinePointsArray(sh), MatrixOperations.createScalingMatrix(r, r * D * (v ? (-1) : 1)));
                        double[][] se = MatrixOperations.multiply(first, MatrixOperations.createRotationMatrix(fi));
                        Line created = new Line(se[0][0], se[0][1], se[1][0], se[1][1]);
                        created = transferLine(created, ab.getStartX(), ab.getStartY());
                        Ls.add(created);
                    }
                    flag = true;
                } else {
                    Ls.add(ab);
                }
            }
            o = Ls;
            if (flag == false) {
                break;
            }
        }
        return o;
    }

    public static List<Line> teragonRecursive(List<Line> o, List<Line> s,
            double delta, int it, int D, int Di, int Do) {

        if (it == 0) {
            return o;
        }
        List<Line> Ls = new ArrayList<>();
        for (int k = 0; k < o.size(); k++) {
            Line ab = o.get(k);
            double r = lineLenght(ab);
            if (!(r < delta)) {
                double fi = arg(ab.getEndX() - ab.getStartX(), ab.getEndY() - ab.getStartY());
                boolean v = false;
                if ((Di == 1 && it % 2 == 0) || (Di == -1 && it % 2 != 0)) {
                    v = !v;
                }
                if ((Do == 1 && k % 2 == 0) || (Do == -1 && k % 2 != 0)) {
                    v = !v;
                }
                for (int j = 0; j < s.size(); j++) {
                    Line sh = s.get(j);
                    double[][] first = MatrixOperations.multiply(getLinePointsArray(sh), MatrixOperations.createScalingMatrix(r, r * D * (v ? (-1) : 1)));
                    double[][] se = MatrixOperations.multiply(first, MatrixOperations.createRotationMatrix(fi));
                    Line created = new Line(se[0][0], se[0][1], se[1][0], se[1][1]);
                    created = transferLine(created, ab.getStartX(), ab.getStartY());
//                    Ls.add(created);
                    List<Line> as = new ArrayList<>();
                    as.add(created);
                    Ls.addAll(teragonRecursive(as, s, delta, --it, D, Di, Do));
                }
            } else {
                Ls.add(ab);
            }
        }
        o = Ls;
        return o;
    }

    public static List<Point2D> kohaLineIter(List<Point2D> points, int d, double delta, int it) {
        double[][] rotMatrix = MatrixOperations.createRotationMatrix(-d * Math.PI / 3);
        for (int i = 0; i < it; i++) {
            List<Point2D> lp = new ArrayList<>();
            if (points.get(0) != null) {
                lp.add(points.get(0));
            }
            boolean flag = false;
            for (int k = 0; k < points.size() - 1; k++) {
                double Vx = (points.get(k + 1).getX() - points.get(k).getX()) / 3;
                double Vy = (points.get(k + 1).getY() - points.get(k).getY()) / 3;
                double length = Math.pow(Math.pow(Vy * 3, 2)
                        + Math.pow(Vx * 3, 2), 0.5);
                if (!(Math.abs(length) < delta)) {
                    Point2D c = new Point2D.Double(points.get(k).getX() + Vx, points.get(k).getY() + Vy);
                    Point2D dP = new Point2D.Double(c.getX() + Vx, c.getY() + Vy);
                    double[][] res = MatrixOperations.multiply(new double[][]{{Vx, Vy}}, rotMatrix);
                    Point2D e = new Point2D.Double(c.getX() + res[0][0], c.getY() + res[0][1]);
                    lp.add(c);
                    lp.add(e);
                    lp.add(dP);
                    flag = true;
                }
                lp.add(points.get(k + 1));
            }
            points = lp;
            if (flag == false) {
                break;
            }
        }
        return points;
    }

    public static Task<List<Line>> getTeragonBuilder(List<Line> o, List<Line> s,
            double delta, int it, int D, int Di, int Do) {
        return new TeragonBuilderTask(o, s, delta, it, D, Di, Do);
    }

    public static class TeragonBuilderTask extends Task<List<Line>> {

        private List<Line> o;
        private List<Line> s;
        private double delta;
        private int it;
        private int D;
        private int Di;
        private int Do;

        public TeragonBuilderTask(List<Line> o, List<Line> s,
                double delta, int it, int D, int Di, int Do) {
            this.o = o;
            this.s = s;
            this.delta = delta;
            this.it = it;
            this.D = D;
            this.Di = Di;
            this.Do = Do;
        }

        @Override
        protected List<Line> call() throws Exception {
            for (int i = 0; i < it; i++) {
                List<Line> Ls = new ArrayList<>();
                boolean flag = false;
                for (int k = 0; k < o.size(); k++) {
                    Line ab = o.get(k);
                    double r = lineLenght(ab);
                    if (!(r < delta)) {
                        double fi = arg(ab.getEndX() - ab.getStartX(), ab.getEndY() - ab.getStartY());
                        boolean v = false;
                        if ((Di == 1 && i % 2 == 0) || (Di == -1 && i % 2 != 0)) {
                            v = !v;
                        }
                        if ((Do == 1 && k % 2 == 0) || (Do == -1 && k % 2 != 0)) {
                            v = !v;
                        }

                        for (int j = 0; j < s.size(); j++) {
                            Line sh = s.get(j);
                            double[][] first = MatrixOperations.multiply(getLinePointsArray(sh),
                                    MatrixOperations.createScalingMatrix(r, r * D * (v ? (-1) : 1)));
                            double[][] se = MatrixOperations.multiply(first, MatrixOperations.createRotationMatrix(fi));
                            Line created = new Line(se[0][0], se[0][1], se[1][0], se[1][1]);
                            created = transferLine(created, ab.getStartX(), ab.getStartY());
                            switch (j) {
                                case 0:
                                    created.setStroke(Color.BLUE);
                                    break;
                                case 1:
                                    created.setStroke(Color.ORANGE);
                                    break;
                                case 2:
                                    created.setStroke(Color.GREEN);
                                    break;
                                case 3:
                                    created.setStroke(Color.CORAL);
                                    break;
                                default:
                            }
                            Ls.add(created);
                        }
                        flag = true;
                    } else {
                        Ls.add(ab);
                    }
                }
                o = Ls;
                if (flag == false) {
                    break;
                }
            }
            return o;
        }
    }
}
