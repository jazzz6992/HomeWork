package task3.atm;


import task3.atm.functions.BankName;
import task3.atm.functions.DenominationsAmountInformation;
import task3.atm.functions.FabricantName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Atm atmAdvanced;
        Atm atmSimple;
        List<Denomination> denom1 = new ArrayList<>();
        denom1.add(new Denomination(100, 5));
        denom1.add(new Denomination(50, 5));
        denom1.add(new Denomination(20, 5));
        List<Denomination> denom2 = new ArrayList<>();
        denom2.add(new Denomination(100, 5));
        denom2.add(new Denomination(50, 5));
        denom2.add(new Denomination(20, 5));
        atmAdvanced = new AtmWithBlackJackAndHookers(denom1, true, "VTB24", "Some handy guy");
        atmSimple = new AtmSimple(denom2);


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
        System.out.println(((BankName) atmAdvanced).getBankName());
        System.out.println("*****************");
        System.out.println(((FabricantName) atmAdvanced).getFabricantName());
        System.out.println("*****************");


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
                    System.out.println("Введите сумму:");
                    int amount = Integer.parseInt(reader.readLine());
                    if (choice.equals("1")) {
                        atmAdvanced.deposit(amount);
                    } else if (choice.equals("2")) {
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
