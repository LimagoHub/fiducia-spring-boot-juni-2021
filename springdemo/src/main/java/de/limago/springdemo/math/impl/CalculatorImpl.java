package de.limago.springdemo.math.impl;


import de.limago.springdemo.math.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public double add(double a, double b) {
        return a + b;
    }
    @Override
    public double sub(double a, double b) {
        return a - b;
    }
}
