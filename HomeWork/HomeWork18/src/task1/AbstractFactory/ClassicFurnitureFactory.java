package task1.AbstractFactory;

import task1.AbstractFactory.furniture.Chair;
import task1.AbstractFactory.furniture.Table;
import task1.AbstractFactory.furniture.classic.ClassicChair;
import task1.AbstractFactory.furniture.classic.ClassicTable;

public class ClassicFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair(String material, int height, int width, int lenght) {
        return new ClassicChair(material, height, width, lenght);
    }

    @Override
    public Table createTable(String material, int height, int width, int lenght) {
        return new ClassicTable(material, height, width, lenght);
    }
}
