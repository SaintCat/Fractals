/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals.basic;

/**
 *
 * @author Chernyshov
 */
public enum ShapeType {

    LINE,
    SQUARE,
    TRIANGLE,
    HEXAGON;

    @Override
    public String toString() {
        switch (this) {
            case LINE:
                return "Линия";
            case SQUARE:
                return "Квадрат";
            case TRIANGLE:
                return "Треугольник";
            case HEXAGON:
                return "Шестиугольник";
            default:
                return "";
        }
    }
}
