package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm(390);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Нажмите \"1\" для зачисления, \"2\" для снятия или \"q\" для выхода");
            try {
                String choice = reader.readLine();
                if (choice.equals("q")) {
                    return;
                }
                System.out.println("Введите сумму:");
                int amount = Integer.parseInt(reader.readLine());
                if (choice.equals("1")) {
                    atm.deposit(amount);
                } else if (choice.equals("2")) {
                    atm.withdraw(amount);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода суммы");
            }

        }
    }
}
