package task2.controller;

import task2.*;
import task2.exceptions.IlluminanceTooMuchException;
import task2.exceptions.SpaceUsageTooMuchException;
import task2.exceptions.WrongLightException;
import task2.view.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static task2.view.Ui.getPositiveInt;

public class Manager {
    private Ui ui;
    private List<Building> buildings = new ArrayList<>();

    public Manager(Ui ui) {
        this.ui = ui;
    }

    public void processBuildingMenuChoice() throws IOException {
        switch (getPositiveInt()) {
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
                ui.printRoomMenu();
                buildingContin = processRoomMenuChoice(current);
            }
        } else {
            ui.print("Список зданий пуст");
        }
    }

    private boolean processRoomMenuChoice(Building current) throws IOException {
        try {
            switch (getPositiveInt()) {
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
            System.out.println(e.getMessage());
        }
        return true;
    }

    private void chooseRoom(Building building) throws IOException {
        Room room = (Room) choose(building.getRooms());
        if (room != null) {
            boolean roomContin = true;
            while (roomContin) {
                ui.printInteriorMenu();
                roomContin = processInteriorMenuChoice(room);
            }
        } else {
            ui.print("Список комнат пуст");
        }
    }

    private boolean processInteriorMenuChoice(Room room) throws IOException {
        switch (getPositiveInt()) {
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
        buildings.add(new Building(Ui.getString()));
    }

    private void addRoom(Building building) throws IOException, WrongLightException {
        ui.print("Введите название комнаты");
        String name = Ui.getString();
        int square;
        ui.print("Введите площадь комнаты");
        square = Ui.getPositiveInt();
        ui.print("Введите кол-во окон");
        int windows = getPositiveInt();
        building.addRoom(name, square, windows);
    }

    private void addFurniture(Room room) throws IOException {
        ui.print("Введите название");
        String name = Ui.getString();
        ui.print("Введите площадь");
        int square = Ui.getPositiveInt();
        try {
            room.add(new Furniture(name, square));
        } catch (SpaceUsageTooMuchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addLightBuld(Room room) throws IOException {
        ui.print("Введите мощность лампы");
        int light = Ui.getPositiveInt();
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
            System.out.println(i + 1 + ") " + obj.get(i).toString());
        }
        ui.print("Введите номер");
        while (true) {
            int choice = getPositiveInt() - 1;
            if (choice < 0 || choice >= obj.size()) {
                ui.print("Вы ввели неправильный номер");
            } else {
                return obj.get(choice);
            }
        }
    }
}
