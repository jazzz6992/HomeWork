package task3;

public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 3, -5, 7, -6, 5, 7, 3, 7, -20};
        System.out.print("| ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " | ");
        }
        System.out.println();
        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] += arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = arr[i] - arr[arr.length - 1 - i];
            arr[i] = arr[i] - arr[arr.length - 1 - i];
        }
        System.out.print("| ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " | ");
        }
    }
}
