package task2.view;

import com.sun.istack.internal.NotNull;
import task2.controller.BuildingManagerImplimentation;
import task2.interfaces.BuildingManager;
import task2.interfaces.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UiImplimentation implements Ui {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Ui ui = new UiImplimentation();
        ui.print("Введите \"quit\" в любой момент для выхода из программы");
        BuildingManager manager = new BuildingManagerImplimentation(ui);
        while (true) {
            manager.process();
        }
    }

    public void printMenu(String... strings) {
        print("Нажмите:");
        for (int i = 0; i < strings.length; i++) {
            print(String.format("%d - %s", i + 1, strings[i]));
        }
    }

    @NotNull
    public String getString() throws IOException {
        String result = reader.readLine();
        if (result.equals("quit")) {
            System.exit(0);
            return "";
        } else {
            return result;
        }
    }

    public int getPositiveInt() throws IOException {
        try {
            int i = Integer.parseInt(getString());
            if (i <= 0) {
                System.out.println("Введите положтельное число");
                return getPositiveInt();
            }
            return i;
        } catch (NumberFormatException e) {
            System.out.println("Введите цифру");
            return getPositiveInt();
        }
    }

    public void print(String s) {
        System.out.println(s);
    }
}
