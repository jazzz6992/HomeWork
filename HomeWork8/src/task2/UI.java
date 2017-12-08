package task2;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UI {
    private static String[][] forms = new String[5][3];
    private static String format;
    private static final File RESOURCE = new File("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/HomeWork8/src/task2/RESOURCE");

    static {
        forms[0][0] = "лет";
        forms[0][1] = "год";
        forms[0][2] = "года";
        forms[1][0] = "месяцев";
        forms[1][1] = "месяц";
        forms[1][2] = "месяца";
        forms[2][0] = "дней";
        forms[2][1] = "день";
        forms[2][2] = "дня";
        forms[3][0] = "часов";
        forms[3][1] = "час";
        forms[3][2] = "часа";
        forms[4][0] = "минут";
        forms[4][1] = "минута";
        forms[4][2] = "минуты";
    }

    public static void main(String[] args) throws IOException {
        StudentManager manager = new MyManager();
        System.out.println("Введите формат даты или оставьте поле пустым для использования формата yyyy-M-d");
        format = ConsoleHelper.getString();
        if ("".equals(format)) {
            format = "yyyy-M-d";
        }
        if (RESOURCE.exists() && RESOURCE.isFile()) {
            System.out.println("Загружаем из базы");
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RESOURCE));
                List<Student> students = (List<Student>) ois.readObject();
                ois.close();
                manager.setStudents(students);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Создаем базу");
            try {
                String contin;
                do {
                    addStudent(manager);
                    System.out.println("Чтобы продолжить нажмите \"y\"");
                    contin = ConsoleHelper.getString();
                } while (contin.equals("y"));
                manager.saveStudents(RESOURCE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printLifetime(manager.getAvarageLifetime());
        while (true) {
            System.out.println("Введите \"+\" для добавления студента, \"-\" для удаления или любое другое сочетание клавиш для выхода");
            try {
                switch (ConsoleHelper.getString()) {
                    case "+":
                        addStudent(manager);
                        break;
                    case "-":
                        removeStudent(manager);
                        break;
                    default:
                        manager.saveStudents(RESOURCE);
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printLifetime(GregorianCalendar c) {
        StringBuilder sb = new StringBuilder();
        String[] forms = new String[5];
        int[] numbersForForm = new int[5];
        numbersForForm[0] = c.get(GregorianCalendar.YEAR);
        numbersForForm[1] = c.get(GregorianCalendar.MONTH);
        numbersForForm[2] = c.get(GregorianCalendar.DAY_OF_MONTH);
        numbersForForm[3] = c.get(GregorianCalendar.HOUR_OF_DAY);
        numbersForForm[4] = c.get(GregorianCalendar.MINUTE);
        for (int i = 0; i < 5; i++) {
            forms[i] = getCorrectForm(i, numbersForForm[i]);
        }
        sb.append(numbersForForm[0]).append(" ").append(forms[0]).append(" ")
                .append(numbersForForm[1]).append(" ").append(forms[1]).append(" ")
                .append(numbersForForm[2]).append(" ").append(forms[2]).append(" ")
                .append(numbersForForm[3]).append(" ").append(forms[3]).append(" ")
                .append(numbersForForm[4]).append(" ").append(forms[4]);
        System.out.println(sb.toString());
    }

    public static String getCorrectForm(int count, int numberForForm) {
        String result = forms[count][0];
        if (numberForForm >= 11 && numberForForm <= 14) {
            return result;
        }
        switch (numberForForm % 10) {
            case 1:
                result = forms[count][1];
                break;
            case 2:
            case 3:
            case 4:
                result = forms[count][2];
                break;
        }
        return result;
    }

    public static boolean addStudent(StudentManager manager) throws IOException {
        try {
            manager.addStudent(format);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Скорее всего, вы задали неправильный формат. Задайте его заново");
            format = ConsoleHelper.getString();
            return false;
        }
    }

    public static boolean removeStudent(StudentManager manager) throws IOException {
        System.out.println("Введите часть имени или фамилии студента для удаления");
        String key = ConsoleHelper.getString();
        List<Student> forRemove = new ArrayList<>();
        int choice = 0;
        for (Student s :
                manager.getStudents()) {
            if (s.getFirstName().contains(key) || s.getLastName().contains(key)) {
                forRemove.add(s);
            }
        }
        if (forRemove.size() == 0) {
            System.out.println("Такой студент не найден");
            return false;
        }
        if (forRemove.size() > 1) {
            System.out.println("Выберите студента для удаления");
            for (int i = 0; i < forRemove.size(); i++) {
                System.out.println(i + 1 + " " + forRemove.get(i).toString());
            }
            do {
                System.out.println("Введите номер от 1 до " + forRemove.size());
                choice = ConsoleHelper.getInt() - 1;
            } while (choice < 0 || choice >= forRemove.size());
        }
        if (manager.removeStudent(forRemove.get(choice))) {
            System.out.println("Студент " + forRemove.get(choice).toString() + " удален");
            return true;
        } else {
            System.out.println("Ошибка удаления студента");
            return false;
        }
    }
}
