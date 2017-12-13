package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        String current;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите строку для добавления или \"quit\" для выхода");
            while (!(current = reader.readLine()).equals("quit")) {
                strings.add(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).replaceAll("[aа]", ""));
        }
        System.out.println(strings.toString());
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(strings);
        strings.clear();
        strings.addAll(stringSet);
        System.out.println(strings.toString());
    }
}
