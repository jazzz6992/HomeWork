package task5;

public class Main {
    public static void main(String[] args) {
        String text = "5. Имеется строка, которая содержит символы ? и символы #.  Замените все символы ? на HELLO, а # - удалите. Результат вывести на экран.";
        text = text.replaceAll("[?]", "HELLO");
        text = text.replaceAll("#", "");
        System.out.println(text);
    }
}
