/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals.basic;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Line;

/**
 *
 * @author Chernyshov
 */
public class PatternFactory {

    public static List<Line> getLines(PatternType type) {
        switch (type) {
            case KOHA_LINE:
                return BasicPatterns.getKohaLinePattern();
            case GOSPER_LINE_1:
                return BasicPatterns.getGosperLine6Pattern();
            case GOSPER_LINE_2:
                return BasicPatterns.getGosperLine4Pattern();
            case GOSPER_LINE_3:
                return BasicPatterns.getGosperLine3Pattern();
            case MANDELBROT_GIVEN:
                return BasicPatterns.getMandelbrotGivenPattern();
            case MANDELBROT_GIVEN_2:
                return BasicPatterns.getMandelbrotGiven2Pattern();
            case PEANO_LINE:
                return BasicPatterns.getPeanoLinePattern();
            case PEANO_LINE_2:
                return BasicPatterns.getPeanoLine2Pattern();
            case PEANO_LINE_3:
                return BasicPatterns.getPeanoLine3Pattern();
            case PEANO_LINE_4:
                return BasicPatterns.getPeanoLine4Pattern();
            default:
                return new ArrayList<>();
        }
    }
}
