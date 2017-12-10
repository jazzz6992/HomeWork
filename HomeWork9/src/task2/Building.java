package task2;

import task2.exceptions.WrongLightException;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String name;
    private List<Room> rooms;

    public Building(String name) {
        this.name = name;
        rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(String name, int square, int windows) throws WrongLightException {
        Room room = new Room(name, square, windows);
        rooms.add(room);
    }

    public Room getRoom(int i) {
        if (i < 0 || i >= rooms.size()) {
            return null;
        }
        return rooms.get(i);
    }

    public void describe() {
        System.out.println(name);
        for (Room room :
                rooms) {
            room.describe();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
