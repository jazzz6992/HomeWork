package task1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Введите целое число");
            arr[i] = getNumber();
        }
        printArray(arr);
        sort(arr);
        printArray(arr);
        printArray(findEven(arr));
    }

    public static int getNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        try {
            result = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("это не целое число");
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return getNumber();
        }
        return result;
    }

    public static void printArray(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i] + " | ");
        }
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        System.out.println(stringBuilder.toString());
    }

    public static int[] findEven(int[] arr) {
        List<Integer> evens = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evens.add(arr[i]);
            }
        }
        int[] r = new int[evens.size()];
        for (int i = 0; i < evens.size(); i++) {
            r[i] = evens.get(i);
        }
        return r;
    }

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                } else {
                    break;
                }
            }
        }
    }
}
