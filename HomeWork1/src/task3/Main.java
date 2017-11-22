package task3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        double a = 0;
        double b = 0;
        double r = 0;

        System.out.println("Задайте длину прямоугольника (положительное число)");
        a = getPositiveNumber();
        System.out.println("Задайте ширину прямоугольника (положительное число)");
        b = getPositiveNumber();
        System.out.println("Задайте радиус (положительное число)");
        r = getPositiveNumber();

        if (Math.sqrt((a * a) + (b * b)) / 2 <= r) {
            System.out.println("Отверстие можно закрыть");
        } else {
            System.out.println("Отверстие закрыть нельзя");
        }
    }

    public static double getPositiveNumber() {
        Scanner scanner = new Scanner(System.in);
        try {
            double result = scanner.nextDouble();
            return result;
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод. Попробуйте снова");
            return getPositiveNumber();
        }
    }
}
