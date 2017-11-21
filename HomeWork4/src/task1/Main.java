package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Patient[] patients = new Patient[4];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < patients.length; i++) {
                System.out.println("Введите имя:");
                String name = reader.readLine();
                System.out.println("Введите группу крови (1-4):");
                int type = 0;
                do {
                    try {
                        type = Integer.parseInt(reader.readLine());
                    } catch (NumberFormatException e) {

                    }
                    if (type < 1 || type > 4) {
                        System.out.println("Повторите ввод");
                    }
                } while (type < 1 || type > 4);
                System.out.println("Введите резус-фактор (+/-):");
                String factor;
                do {
                    factor = reader.readLine();
                } while (!factor.equals("+") && !factor.equals("-"));
                String res = type + factor;
                Patient.BloodType bloodType = Patient.BloodType.FIRST_PLUS;
                switch (res) {
                    case "1+":
                        bloodType = Patient.BloodType.FIRST_PLUS;
                        break;
                    case "1-":
                        bloodType = Patient.BloodType.FIRST_MINUS;
                        break;
                    case "2+":
                        bloodType = Patient.BloodType.SECOND_PLUS;
                        break;
                    case "2-":
                        bloodType = Patient.BloodType.SECOND_MINUS;
                        break;
                    case "3+":
                        bloodType = Patient.BloodType.THIRD_PLUS;
                        break;
                    case "3-":
                        bloodType = Patient.BloodType.THIRD_MINUS;
                        break;
                    case "4+":
                        bloodType = Patient.BloodType.FOURTH_PLUS;
                        break;
                    case "4-":
                        bloodType = Patient.BloodType.FOURTH_MINUS;
                        break;
                }
                System.out.println("Введите возраст:");
                int age = -1;
                do {
                    try {
                        age = Integer.parseInt(reader.readLine());
                    } catch (NumberFormatException e) {

                    }
                    if (age < 0) {
                        System.out.println("Повторите ввод");
                    }
                } while (age < 0);
                System.out.println("Укажите пол (м/ж):");
                boolean sex;
                String s;
                do {
                    s = reader.readLine();
                } while (!s.equals("м") && !s.equals("ж"));
                if (s.equals("м")) {
                    sex = true;
                } else {
                    sex = false;
                }
                System.out.println("Укажите диагноз");
                String diag = reader.readLine();
                patients[i] = new Patient(name, bloodType, age, sex, diag);
                System.out.println(patients[i]);
            }
            String choice;
            do {
                System.out.println("нажмите \"в\" для поиска по возрасту, \"и\" жля поиска по имени или оставьте поле пустым для выхода:");
                choice = reader.readLine();
                if (choice.equals("")) {
                    return;
                }
                if (choice.equals("в")) {
                    int ageToFind;
                    System.out.println("Введите возраст для поиска");
                    try {
                        ageToFind = Integer.parseInt(reader.readLine());
                        for (Patient p :
                                patients) {
                            if (p.getAge() == ageToFind) {
                                System.out.println(p);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка ввода возраста");
                    }
                } else if (choice.equals("и")) {
                    String nameToFind;
                    System.out.println("Введите имя для поиска");
                    nameToFind = reader.readLine();
                    for (Patient p :
                            patients) {
                        if (p.getFullName().toLowerCase().contains(nameToFind.toLowerCase())) {
                            System.out.println(p);
                        }
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
