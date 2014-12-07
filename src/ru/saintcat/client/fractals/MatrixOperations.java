/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals;

/**
 *
 * @author Chernyshov
 */
public class MatrixOperations {

    public static double[][] createScalingMatrix(double mx, double my) {
        double[][] res = new double[2][2];
        res[0][0] = mx;
        res[0][1] = 0;
        res[1][0] = 0;
        res[1][1] = my;
        return res;
    }

    public static double[][] createRotationMatrix(double angle) {
        double[][] res = new double[2][2];
        res[0][0] = Math.cos(angle);
        res[0][1] = Math.sin(angle);
        res[1][0] = -Math.sin(angle);
        res[1][1] = Math.cos(angle);
        return res;
    }

    public static double[][] createRotatinMatrix3(double angle) {
        double[][] res = new double[3][3];
        res[0][0] = Math.cos(angle);
        res[0][1] = -Math.sin(angle);
        res[1][2] = 0;
        res[1][0] = Math.sin(angle);
        res[1][1] = Math.cos(angle);
        res[1][2] = 0;
        res[2][0] = 0;
        res[2][1] = 0;
        res[2][2] = 1;

        return res;
    }

    public static double[][] multiply(double[][] mA, double[][] mB) {
        int m = mA.length;
        int n = mB[0].length;
        int o = mB.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += mA[i][k] * mB[k][j];
                }
            }
        }
        return res;
    }

    public static double[][] rotatePoint(double x, double y, double sX, double sY, double angle) {
        double[][] s = new double[][]{{1, 0, 0}, {0, 1, 0}, {-sX, -sY, 1}};
        double[][] f = new double[][]{{1, 0, 0}, {0, 1, 0}, {sX, sY, 1}};

        double[][] asd = multiply(new double[][]{{x, y, 1}}, s);
        double[][] asd2 = multiply(asd, createRotatinMatrix3(angle));
        double[][] asd3 = multiply(asd2, f);

        return new double[][]{{asd3[0][0], asd3[0][1]}};
    }

    public static double[][] createTransferMatrix(double dX, double dY) {
        double[][] res = new double[3][3];

        res[0][0] = 1;
        res[0][1] = 0;
        res[0][2] = dX;
        res[1][0] = 0;
        res[1][1] = 1;
        res[1][2] = dY;
        res[2][0] = 0;
        res[2][1] = 0;
        res[2][2] = 1;

        return res;
    }

    public static double[][] transpose(double[][] matrix) throws IllegalArgumentException {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int rowLength = matrix[0].length;
        for (double[] ai : matrix) {
            if (rowLength != ai.length) {
                throw new IllegalArgumentException("Non-equal rows");
            }
        }

        double[][] tMatrix = new double[rowLength][];
        for (int i = 0; i < rowLength; i++) {
            tMatrix[i] = new double[matrix.length];
        }
        for (int i = 0; i < matrix.length; i++) {
            double[] tArr = matrix[i];
            for (int j = 0; j < rowLength; j++) {
                tMatrix[j][i] = tArr[j];
            }
        }
        return tMatrix;
    }
}
