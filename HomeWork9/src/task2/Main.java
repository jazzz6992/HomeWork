package task2;

import task2.exceptions.IlluminanceTooMuchException;
import task2.exceptions.SpaceUsageTooMuchException;
import task2.exceptions.WrongLightException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("Введите \"quit\" в любой момент для выхода из программы");
        List<Building> buildings = new ArrayList<>();
        while (true) {
            System.out.println("Нажмите:");
            System.out.println("1 - для выбора здания");
            System.out.println("2 - для добавления здания");
            System.out.println("3 - для вывода информации о каждом здании");
            switch (getPositiveInt()) {
                case 1:
                    Building current = (Building) choose(buildings);
                    if (current != null) {
                        boolean buildingContin = true;
                        while (buildingContin) {
                            System.out.println("Нажмите:");
                            System.out.println("1 - для выбора комнаты");
                            System.out.println("2 - для добавления комнаты");
                            System.out.println("3 - просмотра информации");
                            System.out.println("4 - для возврата к предыдущему меню");
                            try {
                                switch (getPositiveInt()) {
                                    case 1:
                                        Room room = (Room) choose(current.getRooms());
                                        if (room != null) {
                                            boolean roomContin = true;
                                            while (roomContin) {
                                                System.out.println("Нажмите:");
                                                System.out.println("1 - для добавления предметов мебели");
                                                System.out.println("2 - для добавления освещения");
                                                System.out.println("3 - просмотра информации");
                                                System.out.println("4 - для возврата к предыдущему меню");
                                                switch (getPositiveInt()) {
                                                    case 1:
                                                        System.out.println("Введите название");
                                                        String name = getString();
                                                        System.out.println("Введите площадь");
                                                        int square = getPositiveInt();
                                                        try {
                                                            room.add(new Furniture(name, square));
                                                        } catch (SpaceUsageTooMuchException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                        break;
                                                    case 2:
                                                        System.out.println("Введите мощность лампы");
                                                        int light = getPositiveInt();
                                                        try {
                                                            room.add(new Lightbulb(light));
                                                        } catch (IlluminanceTooMuchException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                        break;
                                                    case 3:
                                                        room.describe();
                                                        break;
                                                    case 4:
                                                        roomContin = false;
                                                        break;
                                                }
                                            }
                                        } else {
                                            System.out.println("Список комнат пуст");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Введите название комнаты");
                                        String name = getString();
                                        int square;
                                        System.out.println("Введите площадь комнаты");
                                        square = getPositiveInt();
                                        System.out.println("Введите кол-во окон");
                                        int windows = getPositiveInt();
                                        current.addRoom(name, square, windows);
                                        break;
                                    case 3:
                                        current.describe();
                                        break;
                                    case 4:
                                        buildingContin = false;
                                        break;
                                }
                            } catch (WrongLightException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Список зданий пуст");
                    }
                    break;
                case 2:
                    addBuilding(buildings);
                    break;
                case 3:
                    for (Building b :
                            buildings) {
                        b.describe();
                    }
                    break;
            }
        }
    }

    private static String getString() throws IOException {
        String result = reader.readLine();
        if (result.equals("quit")) {
            System.exit(0);
            return null;
        } else {
            return result;
        }
    }

    private static int getPositiveInt() throws IOException {
        try {
            int i = Integer.parseInt(getString());
            if (i <= 0) {
                System.out.println("Введите положтельное число");
                return getPositiveInt();
            }
            return i;
        } catch (NumberFormatException e) {
            System.out.println("Введите цифру");
            return getPositiveInt();
        }
    }

    private static Object choose(List<?> obj) throws IOException {
        if (obj.size() == 0) {
            return null;
        }
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(i + 1 + ") " + obj.get(i).toString());
        }
        System.out.println("Введите номер");
        while (true) {
            int choice = getPositiveInt() - 1;
            if (choice < 0 || choice >= obj.size()) {
                System.out.println("Вы ввели неправильный номер");
            } else {
                return obj.get(choice);
            }
        }
    }

    private static void addBuilding(List<Building> buildings) throws IOException {
        System.out.println("Введите название здания");
        buildings.add(new Building(getString()));
    }
}
