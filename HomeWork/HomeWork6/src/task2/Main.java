package task2;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        int[] arr2 = new int[20];
        System.arraycopy(arr, 0, arr2, arr2.length / 2 - arr.length / 2, arr.length);
        printArr(arr);
        printArr(arr2);
    }

    private static void printArr(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i :
                arr) {
            stringBuilder.append(i).append(" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
