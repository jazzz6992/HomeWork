package task5;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (tmp < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
