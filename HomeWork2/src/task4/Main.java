package task4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int i = scanner.nextInt();
            String test = "" + i;
            boolean isOk = true;
            for (int j = 0; j < test.length() - 1; j++) {
                if (test.charAt(j) > test.charAt(j + 1)) {
                    System.out.println(i + " не образуют");
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                System.out.println(i + " образуют");
            }
        } catch (InputMismatchException e) {
            System.out.println("это не целое число");
        }
    }
}
