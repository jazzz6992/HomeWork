package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<FullName> men = new ArrayList<>();
        List<FullName> women = new ArrayList<>();
        men.add(new FullName("Василий", "Иванович", "Пупкин"));
        men.add(new FullName("Федор", "Христофорович", "Чичиков"));
        men.add(new FullName("Роман", "Романович", "Романюк"));
        men.add(new FullName("Акакий", "Онаньевич", "Юсупов"));
        women.add(new FullName("Ефросинья", "Ивановна", "Чернова-Водкина"));
        women.add(new FullName("Рахиль", "Эфраимовна", "Блохина"));
        women.add(new FullName("Ванесса", "Багратионовна", "Пальчикова"));
        women.add(new FullName("Вера", "Надеждовна", "Любвина"));

        Map<String, List<FullName>> names = new HashMap<>();
        names.put("man", men);
        names.put("woman", women);
        System.out.println("Введите \"man\" для вывода мужского имени, \"woman\" - для вывода женского имени или что-либо другое для выхода");
        boolean isOk = true;
        String choice;
        Random random = new Random();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (isOk) {
                choice = reader.readLine();
                if (choice.equals("man")) {
                    System.out.println(names.get("man").get(random.nextInt(names.get("man").size())));
                } else if (choice.equals("woman")) {
                    System.out.println(names.get("woman").get(random.nextInt(names.get("woman").size())));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
