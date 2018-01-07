package task1;

import task1.exceptions.DivisionByZeroException;

public class Calculator {
    public static double add(double a, double b) {
        return a + b;
    }

    public static double substract(double a, double b) {
        return a - b;
    }

    public static double multiple(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) throws DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException("Попытка поделить " + a + " на " + b);
        }
        return a / b;
    }
}
