/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals.enums;

/**
 *
 * @author Chernyshov
 */
public enum ReflectionXAxisBySegmentNumber {

    NO(0),
    EVEN_NUMBERS(1),
    ODD_NUMBERS(-1);

    private int value;

    ReflectionXAxisBySegmentNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        switch (this) {
            case EVEN_NUMBERS:
                return "На четных";
            case NO:
                return "Нет";
            case ODD_NUMBERS:
                return "На нечетных";
            default:
                return "";
        }
    }
}
