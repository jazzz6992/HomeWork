package ui;

import manager.Manager;

public class UiImplimentation implements Ui {
    Manager manager;

    public static void main(String[] args) {
        Ui ui = new UiImplimentation();
        Manager manager = new Manager(ui);
        manager.getFile("http://kiparo.ru/t/stock.xml", "newfile.xml");
    }
}
