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
public enum ReflectionXAxis {

    NO(1),
    YES(-1);

    private int value;

    ReflectionXAxis(int value) {
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
            case YES:
                return "Да";
            case NO:
                return "Нет";
            default:
                return "";
        }
    }
}
