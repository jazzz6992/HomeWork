package task2;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        float[] arr = {2, 3, 5, 7, 6, 5, 7, 3, 7, 20};
        Map<Float, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (Map.Entry<Float, Integer> entry :
                map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("[" + entry.getKey() + "] - повторений " + entry.getValue());
            }
        }
    }
}
