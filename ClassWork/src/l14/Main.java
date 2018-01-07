package l14;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        int[] arr = new int[100000001];
        for (int i = 0, j = 0; i < arr.length - 1; i++) {
            arr[i] = j;
            if (i % 2 != 0) {
                j++;
            }
        }
        arr[100000000] = 555555;
        long start = System.currentTimeMillis();
        findMatch(arr);
        long finish = System.currentTimeMillis();
        System.out.println("time = " + (finish - start));
        start = System.currentTimeMillis();
        findMatch1(arr);
        finish = System.currentTimeMillis();
        System.out.println("time = " + (finish - start));
    }

    public static void findMatch(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = res ^ arr[i];
        }
        System.out.println(res);
    }

    public static void findMatch1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 0) {
                System.out.println(e.getKey());
            }
        }
    }
}
