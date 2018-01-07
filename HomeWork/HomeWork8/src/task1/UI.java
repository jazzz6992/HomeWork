package task1;

import task1.exceptions.DivisionByZeroException;
import task1.exceptions.NumberParseException;
import task1.exceptions.WrongOperatorException;

import java.io.IOException;

public class UI {
    public static void main(String[] args) {
        boolean isOk = true;
        while (isOk) {
            try {
                double result = 0;
                System.out.println("Введите первое число:");
                double a = ConsoleHelper.getDouble();
                System.out.println("Введите второе число:");
                double b = ConsoleHelper.getDouble();
                System.out.println("Введите операцию (+,-,*,/)");
                switch (ConsoleHelper.getOperator()) {
                    case '+':
                        result = Calculator.add(a, b);
                        break;
                    case '-':
                        result = Calculator.substract(a, b);
                        break;
                    case '*':
                        result = Calculator.multiple(a, b);
                        break;
                    case '/':
                        result = Calculator.divide(a, b);
                        break;
                }
                System.out.print("Результат равен: " + result + "\n");
                System.out.println("Введите \"y\", чтобы продолжить");
                if (!"y".equals(ConsoleHelper.getString())) {
                    isOk = false;
                }
            } catch (NumberParseException | WrongOperatorException | DivisionByZeroException e) {
                System.out.println(e.getRussianMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
