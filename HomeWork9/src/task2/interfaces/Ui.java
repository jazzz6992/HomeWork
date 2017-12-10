package task2.interfaces;

import java.io.IOException;

public interface Ui {
    void print(String describe);

    void printRoomMenu();

    void printInteriorMenu();

    void printBuildengMenu();

    String getString() throws IOException;

    int getPositiveInt() throws IOException;
}
