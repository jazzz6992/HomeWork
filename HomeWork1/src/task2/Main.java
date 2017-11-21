package task2;

public class Main {
    public static void main(String[] args) {
        int random = (int) (Math.random() * 1000);
        System.out.println(random);
        if ((random - 7) % 10 == 0) {
            System.out.println("последнее число - семерка");
        } else {
            System.out.println("последнее число не является семеркой");
        }
    }
}
