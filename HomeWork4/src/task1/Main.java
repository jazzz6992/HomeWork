package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.createTestData();
        String choice;
        do {
            System.out.println("нажмите \"в\" для поиска по возрасту, \"и\" жля поиска по имени или оставьте поле пустым для выхода:");
            choice = askUser();
            if (choice != null) {
                if (choice.equals("")) {
                    System.out.println("Good bye");
                    return;
                }
                if (choice.equals("в")) {
                    printPatientList(manager.findByAge());
                } else if (choice.equals("и")) {
                    printPatientList(manager.findByName());
                }
            }
        } while (true);
    }

    private static void printPatientList(List<Patient> patients) {
        if (patients != null) {
            for (Patient p :
                    patients) {
                System.out.println(p);
            }
        }
    }

    public static String askUser() {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
