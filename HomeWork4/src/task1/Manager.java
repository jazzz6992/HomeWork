package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private Patient[] patients;

    public void createTestData() {
        patients = new Patient[4];
        for (int i = 0; i < patients.length; i++) {
            patients[i] = createPatient();
            System.out.println(patients[i]);
        }
    }

    public Patient createPatient() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите имя:");
            String name = reader.readLine();
            System.out.println("Введите группу крови (1-4):");
            int type = 0;
            do {
                try {
                    type = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Повторите ввод");
                    continue;
                }
                if (type < 1 || type > 4) {
                    System.out.println("Повторите ввод");
                }
            } while (type < 1 || type > 4);
            System.out.println("Введите резус-фактор (+/-):");
            String factor;
            do {
                factor = reader.readLine();
                if (!factor.equals("+") && !factor.equals("-")) {
                    System.out.println("повторите ввод");
                }
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
                    System.out.println("Повторите ввод");
                    continue;
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
                if (!s.equals("м") && !s.equals("ж")) {
                    System.out.println("Повторите ввод");
                }
            } while (!s.equals("м") && !s.equals("ж"));
            sex = s.equals("м");
            System.out.println("Укажите диагноз");
            String diag = reader.readLine();
            return new Patient(name, bloodType, age, sex, diag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> findByAge() {
        int ageToFind;
        List<Patient> result = new ArrayList<>();
        System.out.println("Введите возраст для поиска");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                ageToFind = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("ошибка ввода возраста");
                return result;
            }
            for (Patient p :
                    patients) {
                if (p.getAge() == ageToFind) {
                    result.add(p);
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Patient> findByName() {
        String nameToFind;
        List<Patient> result = new ArrayList<>();
        System.out.println("Введите имя для поиска");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            nameToFind = reader.readLine();
            for (Patient p :
                    patients) {
                if (p.getFullName().toLowerCase().contains(nameToFind.toLowerCase())) {
                    result.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
