package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getString() throws IOException {
        return reader.readLine();
    }

    public static String getFirstName() throws IOException {
        System.out.println("Введите имя");
        return ConsoleHelper.getString();
    }

    public static String getLastName() throws IOException {
        System.out.println("Введите фамилию");
        return ConsoleHelper.getString();
    }

    public static String getBirthDate(String format) throws IOException {
        System.out.println("Введите дату рождения в формате " + format);
        return ConsoleHelper.getString();
    }

    public static int getInt() throws IOException {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Введите число");
            return getInt();
        }
    }
}
