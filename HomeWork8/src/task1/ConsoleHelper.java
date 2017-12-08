package task1;

import task1.exceptions.NumberParseException;
import task1.exceptions.WrongOperatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getString() throws IOException {
        return reader.readLine();
    }

    public static double getDouble() throws IOException {
        String s = getString();
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            throw new NumberParseException("Невозможно перевести строку " + s + " в число");
        }
    }

    public static char getOperator() throws IOException, WrongOperatorException {
        String s = getString();
        if (s.length() == 1) {
            switch (s.charAt(0)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    return s.charAt(0);
            }
        }
        throw new WrongOperatorException("Попытка использования строки " + s + " в качестве оператора");
    }
}
