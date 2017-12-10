package task2.controller;

import task2.*;
import task2.exceptions.IlluminanceTooMuchException;
import task2.exceptions.SpaceUsageTooMuchException;
import task2.exceptions.WrongLightException;
import task2.interfaces.BuildingManager;
import task2.interfaces.Ui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildingManagerImplimentation implements BuildingManager {
    private Ui ui;
    private List<Building> buildings = new ArrayList<>();
    private static final String[] BUILDING_MENU = {"для выбора здания", "для добавления здания", "для вывода информации о каждом здании"};
    private static final String[] ROOM_MENU = {"для выбора комнаты", "для добавления комнаты", "просмотра информации о здании", "для возврата к предыдущему меню"};
    private static final String[] INTERIOR_MENU = {"для добавления предметов мебели", "для добавления освещения", "просмотра информации о комнате", "для возврата к предыдущему меню"};

    public BuildingManagerImplimentation(Ui ui) {
        this.ui = ui;
    }

    public void process() throws IOException {
        ui.printMenu(BUILDING_MENU);
        switch (ui.getPositiveInt()) {
            case 1:
                chooseBuilding();
                break;
            case 2:
                addBuilding(buildings);
                break;
            case 3:
                for (Building b :
                        buildings) {
                    ui.print(b.describe());
                }
                break;
        }
    }

    private void chooseBuilding() throws IOException {
        Building current = (Building) choose(buildings);
        if (current != null) {
            boolean buildingContin = true;
            while (buildingContin) {
                ui.printMenu(ROOM_MENU);
                buildingContin = processRoomMenuChoice(current);
            }
        } else {
            ui.print("Список зданий пуст");
        }
    }

    private boolean processRoomMenuChoice(Building current) throws IOException {
        try {
            switch (ui.getPositiveInt()) {
                case 1:
                    chooseRoom(current);
                    break;
                case 2:
                    addRoom(current);
                    break;
                case 3:
                    ui.print(current.describe());
                    break;
                case 4:
                    return false;
            }
        } catch (WrongLightException e) {
            ui.print(e.getMessage());
        }
        return true;
    }

    private void chooseRoom(Building building) throws IOException {
        Room room = (Room) choose(building.getRooms());
        if (room != null) {
            boolean roomContin = true;
            while (roomContin) {
                ui.printMenu(INTERIOR_MENU);
                roomContin = processInteriorMenuChoice(room);
            }
        } else {
            ui.print("Список комнат пуст");
        }
    }

    private boolean processInteriorMenuChoice(Room room) throws IOException {
        switch (ui.getPositiveInt()) {
            case 1:
                addFurniture(room);
                break;
            case 2:
                addLightBuld(room);
                break;
            case 3:
                ui.print(room.describe());
                break;
            case 4:
                return false;
        }
        return true;
    }

    private void addBuilding(List<Building> buildings) throws IOException {
        ui.print("Введите название здания");
        buildings.add(new Building(ui.getString()));
    }

    private void addRoom(Building building) throws IOException, WrongLightException {
        ui.print("Введите название комнаты");
        String name = ui.getString();
        int square;
        ui.print("Введите площадь комнаты");
        square = ui.getPositiveInt();
        ui.print("Введите кол-во окон");
        int windows = ui.getPositiveInt();
        building.addRoom(name, square, windows);
    }

    private void addFurniture(Room room) throws IOException {
        ui.print("Введите название");
        String name = ui.getString();
        ui.print("Введите площадь");
        int square = ui.getPositiveInt();
        try {
            room.add(new Furniture(name, square));
        } catch (SpaceUsageTooMuchException e) {
            ui.print(e.getMessage());
        }
    }

    private void addLightBuld(Room room) throws IOException {
        ui.print("Введите мощность лампы");
        int light = ui.getPositiveInt();
        try {
            room.add(new Lightbulb(light));
        } catch (IlluminanceTooMuchException e) {
            ui.print(e.getMessage());
        }
    }

    private Object choose(List obj) throws IOException {
        if (obj.size() == 0) {
            return null;
        }
        for (int i = 0; i < obj.size(); i++) {
            ui.print(i + 1 + ") " + obj.get(i).toString());
        }
        ui.print("Введите номер");
        while (true) {
            int choice = ui.getPositiveInt() - 1;
            if (choice < 0 || choice >= obj.size()) {
                ui.print("Вы ввели неправильный номер");
            } else {
                return obj.get(choice);
            }
        }
    }
}
