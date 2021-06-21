package de.application;

import de.client.Client;
import de.common.LoggerProxy;
import de.math.Calculator;
import de.math.impl.CalculatorImpl;
import de.math.impl.CalculatorSecure;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new CalculatorImpl();
        //calc = new CalculatorLogger(calc);
        calc = (Calculator) LoggerProxy.newInstance(calc);
        calc = new CalculatorSecure(calc);
        Client client = new Client(calc);
        client.go();
    }
}
