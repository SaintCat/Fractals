/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals.basic;

import javafx.scene.image.Image;

/**
 *
 * @author Chernyshov
 */
public enum PatternType {

    KOHA_LINE(new Image("kohaLine.png")),
    GOSPER_LINE_1(new Image("gosperLine6.png")),
    GOSPER_LINE_2(new Image("gosperLine4.png")),
    GOSPER_LINE_3(new Image("gosperLine3.png")),
    MANDELBROT_GIVEN(new Image("mandel1.png")),
    MANDELBROT_GIVEN_2(new Image("mandel2.png")),
    PEANO_LINE(new Image("peanoLine.png")),
    PEANO_LINE_2(new Image("peanoLine2.png")),
    PEANO_LINE_3(new Image("peanoLine3.png")),
    PEANO_LINE_4(new Image("peanoLine4.png"));
    private final Image icon;

    PatternType(Image icon) {
        this.icon = icon;
    }

    public Image getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        switch (this) {
            case KOHA_LINE:
                return "Линия Коха";
            case GOSPER_LINE_1:
                return "Линия Госпера для 6угольника";
            case GOSPER_LINE_2:
                return "Линия Госпера для 4угольника";
            case GOSPER_LINE_3:
                return "Линия Госпера для 3угольника";
            case MANDELBROT_GIVEN:
                return "Линия Мандельрота-Гибена";
            case MANDELBROT_GIVEN_2:
                return "Линия Мандельрота-Гибена 2";
            case PEANO_LINE:
                return "Линия Пеано";
            case PEANO_LINE_2:
                return "Линия Пеано 2";
            case PEANO_LINE_3:
                return "Линия Пеано 3";
            case PEANO_LINE_4:
                return "Линия Пеано 4";
            default:
                return "";
        }
    }
}
