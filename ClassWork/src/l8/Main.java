package l8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Student> list = new ArrayList<>();
        String name;
        int age;
        String choice = "y";
        while ("y".equals(choice)) {
            System.out.println("Ведите имя");
            name = reader.readLine();
            System.out.println("Введите возраст");
            try {
                age = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число");
                continue;
            }
            list.add(new Student(name, age));
            System.out.println("Продолжить? y/n");
            choice = reader.readLine();
        }
        for (Student s :
                list) {
            System.out.println(s);
        }

        Student student1 = new Student("Name", 29);
        Student student2 = new Student("Name", 29);

        System.out.println(student1.equals(student2));
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

    }
}
