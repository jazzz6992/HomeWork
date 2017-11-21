package task1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
        int min = arr[0];
        int max = arr[0];
        int minIdx = 0;
        int maxIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIdx = i;
            }
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }
        System.out.println("min value =  " + min);
        System.out.println("max value =  " + max);
        arr[minIdx] = 0;
        arr[maxIdx] = 99;
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i] + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        System.out.println(result);
    }
}
