package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int news = 0;
            System.out.println("Введите кол-во новостей");
            do {
                try {
                    news = Integer.parseInt(reader.readLine());
                    if (news <= 0) {
                        System.out.println("Кол-во новостей должно быть положительным");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода");
                }
            } while (news <= 0);
            System.out.println("Кол-во страниц = " + getNumberOfPages(news));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNumberOfPages(int news) {
        int result = news / 10;
        if (news % 10 > 0) {
            result++;
        }
        return result;
    }
}
