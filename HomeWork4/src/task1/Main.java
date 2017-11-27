package task1;

public class Main {
    public static void main(String[] args) {
        Patient[] patients = new Patient[4];
        for (int i = 0; i < patients.length; i++) {
            patients[i] = Manager.createPatient();
            System.out.println(patients[i]);
        }
        String choice;
        do {
            System.out.println("нажмите \"в\" для поиска по возрасту, \"и\" жля поиска по имени или оставьте поле пустым для выхода:");
            choice = Manager.askUser();
            if (choice != null) {
                if (choice.equals("")) {
                    System.out.println("Good bye");
                    return;
                }
                if (choice.equals("в")) {
                    Manager.printPatientList(Manager.findByAge(patients));
                } else if (choice.equals("и")) {
                    Manager.printPatientList(Manager.findByName(patients));
                }
            }
        } while (true);
    }
}
