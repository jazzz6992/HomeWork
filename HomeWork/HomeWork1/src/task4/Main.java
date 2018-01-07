package task4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int ammount;
        Random random = new Random();
        ammount = random.nextInt(10000);
        String roubles;
        int res = Math.abs(ammount);
        res %= 100;
        if (res >= 11 && res <= 14) {
            roubles = "рублей";
        } else {
            res %= 10;
            if (res == 1) {
                roubles = "рубль";
            } else if (res >= 2 && res <= 4) {
                roubles = "рубля";
            } else {
                roubles = "рублей";
            }
        }
        System.out.println(ammount + " " + roubles);
    }
}
