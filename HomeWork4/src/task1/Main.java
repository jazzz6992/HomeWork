package task1;

public class Main {
    // перенести работу с пользователем в мэйн
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.createTestData();
        String choice;
        do {
            System.out.println("нажмите \"в\" для поиска по возрасту, \"и\" жля поиска по имени или оставьте поле пустым для выхода:");
            choice = manager.askUser();
            if (choice != null) {
                if (choice.equals("")) {
                    System.out.println("Good bye");
                    return;
                }
                if (choice.equals("в")) {
                    manager.printPatientList(manager.findByAge());
                } else if (choice.equals("и")) {
                    manager.printPatientList(manager.findByName());
                }
            }
        } while (true);
    }
}
