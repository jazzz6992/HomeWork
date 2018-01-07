package task1;

public class Main {
    public static void main(String[] args) {
        String text = "Далее выведите на экран количество символов в данной строке. Далее разделите строку пополам (если ровно поделить не выходит то +-1 не страшно), в результате у вас должно быть 2-е новых переменных типа String с частями из изначальной строки. Полученные строки выведите на экран.";
        System.out.println(text.length());
        String first = text.substring(0, text.length() / 2);
        String second = text.substring(first.length());
        System.out.println(first);
        System.out.println(second);
    }
}
