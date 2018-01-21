package task1.AbstractFactory;

import task1.AbstractFactory.furniture.Chair;
import task1.AbstractFactory.furniture.Table;
import task1.AbstractFactory.furniture.modern.ModernChair;
import task1.AbstractFactory.furniture.modern.ModernTable;

public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair(String material, int height, int width, int lenght) {
        return new ModernChair(material, height, width, lenght);
    }

    @Override
    public Table createTable(String material, int height, int width, int lenght) {
        return new ModernTable(material, height, width, lenght);
    }
}
