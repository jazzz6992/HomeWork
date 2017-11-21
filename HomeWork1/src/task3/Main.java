package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) {
        double a = 0;
        double b = 0;
        double r = 0;
        try {
            System.out.println("Задайте длину прямоугольника (положительное число)");
            a = getPositiveNumber();
            System.out.println("Задайте ширину прямоугольника (положительное число)");
            b = getPositiveNumber();
            System.out.println("Задайте радиус (положительное число)");
            r = getPositiveNumber();
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода");
            return;
        }
        if (Math.sqrt((a * a) + (b * b)) / 2 <= r) {
            System.out.println("Отверстие можно закрыть");
        } else {
            System.out.println("Отверстие закрыть нельзя");
        }
    }

    public static double getPositiveNumber() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            double result = Double.parseDouble(reader.readLine());
            if (result > 0) {
                return result;
            } else {
                System.out.println("вы ввели отрицательное число или ноль");
                return getPositiveNumber();
            }
        } catch (NumberFormatException e) {
            System.out.println("введите число");
            return getPositiveNumber();
        }
    }
}
