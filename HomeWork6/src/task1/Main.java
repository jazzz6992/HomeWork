package task1;

import task1.Functions.BankName;
import task1.Functions.DenominationsAmountInformation;
import task1.Functions.FabricantName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Atm atmAdvanced;
        Atm atmSimple;
        List<Integer> denom = new ArrayList<>();
        denom.add(100);
        denom.add(50);
        denom.add(20);
        atmAdvanced = new AtmWithBlackJackAndHookers(denom, 390, "VTB24", "Some handy guy");
        atmSimple = new AtmSimple(denom, 570);


        // просто десонстрация работоспособности через разные интерфейсы
        System.out.println("!!!!!!!!demo!!!!!!!!");
        atmSimple.printTotal();
        System.out.println("*****************");
        atmSimple.deposit(200);
        System.out.println("*****************");
        atmSimple.withdraw(150);
        System.out.println("*****************");
        atmAdvanced.printTotal();
        System.out.println("*****************");
        ((DenominationsAmountInformation) atmAdvanced).printDenominationsAmount();
        System.out.println("*****************");
        System.out.println(((BankName)atmAdvanced).getBankName());
        System.out.println("*****************");
        System.out.println(((FabricantName)atmAdvanced).getFabricantName());
        System.out.println("*****************");



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
                    atmAdvanced.deposit(amount);
                } else if (choice.equals("2")) {
                    atmAdvanced.withdraw(amount);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода суммы");
            }

        }
    }
}
