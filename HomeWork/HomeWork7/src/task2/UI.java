package task2;

import java.io.IOException;
import java.util.*;

public class UI {
    private static String[][] forms = new String[5][3];

    static {
        forms[0][0] = "лет";
        forms[0][1] = "год";
        forms[0][2] = "года";
        forms[1][0] = "месяцев";
        forms[1][1] = "месяц";
        forms[1][2] = "месяца";
        forms[2][0] = "дней";
        forms[2][1] = "день";
        forms[2][2] = "дня";
        forms[3][0] = "часов";
        forms[3][1] = "час";
        forms[3][2] = "часа";
        forms[4][0] = "минут";
        forms[4][1] = "минута";
        forms[4][2] = "минуты";
    }

    public static void main(String[] args) {
        StudentManager manager = new MyManager();
        String format;
        System.out.println("Введите формат даты или оставьте поле пустым для использования формата yyyy-M-d");
        try {
            format = ConsoleHelper.getString();
            if ("".equals(format)) {
                format = "yyyy-M-d";
            }
            String contin = "y";
            do {
                try {
                    manager.addStudent(format);
                    System.out.println("Чтобы продолжить нажмите \"y\"");
                    contin = ConsoleHelper.getString();
                } catch (IllegalArgumentException e) {
                    System.out.println("Скорее всего, вы задали неправильный формат. Задайте его заново");
                    format = ConsoleHelper.getString();
                }
            } while (contin.equals("y"));
            printLifetime(manager.getAvarageLifetime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printLifetime(GregorianCalendar c) {
        StringBuilder sb = new StringBuilder();
        String[] forms = new String[5];
        int[] numbersForForm = new int[5];
        numbersForForm[0] = c.get(GregorianCalendar.YEAR);
        numbersForForm[1] = c.get(GregorianCalendar.MONTH);
        numbersForForm[2] = c.get(GregorianCalendar.DAY_OF_MONTH);
        numbersForForm[3] = c.get(GregorianCalendar.HOUR_OF_DAY);
        numbersForForm[4] = c.get(GregorianCalendar.MINUTE);
        for (int i = 0; i < 5; i++) {
            forms[i] = getCorrectForm(i, numbersForForm[i]);
        }
        sb.append(numbersForForm[0]).append(" ").append(forms[0]).append(" ")
                .append(numbersForForm[1]).append(" ").append(forms[1]).append(" ")
                .append(numbersForForm[2]).append(" ").append(forms[2]).append(" ")
                .append(numbersForForm[3]).append(" ").append(forms[3]).append(" ")
                .append(numbersForForm[4]).append(" ").append(forms[4]);
        System.out.println(sb.toString());
    }

    public static String getCorrectForm(int count, int numberForForm) {
        String result = forms[count][0];
        if (numberForForm >= 11 && numberForForm <= 14) {
            return result;
        }
        switch (numberForForm % 10) {
            case 1:
                result = forms[count][1];
                break;
            case 2:
            case 3:
            case 4:
                result = forms[count][2];
                break;
        }
        return result;
    }
}
