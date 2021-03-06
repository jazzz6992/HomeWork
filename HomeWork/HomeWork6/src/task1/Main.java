package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Atm atmAdvanced;
        List<Denomination> denom1 = new ArrayList<>();
        denom1.add(new Denomination(100, 5));
        denom1.add(new Denomination(50, 5));
        denom1.add(new Denomination(20, 5));
        atmAdvanced = new AtmWithBlackJackAndHookers(denom1, true, "VTB24", "Some handy guy");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Нажмите \"1\" для зачисления, \"2\" для снятия, \"3\" для информации о балансе или \"q\" для выхода");
            try {
                String choice = reader.readLine();
                if (choice.equals("q")) {
                    return;
                }
                if (choice.equals("3")) {
                    atmAdvanced.printTotal();
                } else {
                    if (choice.equals("1")) {
                        System.out.println("Введите номинал");
                        int denom = Integer.parseInt(reader.readLine());
                        System.out.println("Введите кол-во");
                        int amount = Integer.parseInt(reader.readLine());
                        atmAdvanced.deposit(denom, amount);
                    } else if (choice.equals("2")) {
                        System.out.println("Введите сумму");
                        int amount = Integer.parseInt(reader.readLine());
                        atmAdvanced.withdraw(amount);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода суммы");
            }

        }
    }
}
