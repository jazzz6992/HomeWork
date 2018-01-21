package task1.AbstractFactory;

import task1.AbstractFactory.furniture.Chair;
import task1.AbstractFactory.furniture.Table;

public interface FurnitureFactory {
    Chair createChair(String material, int height, int width, int lenght);

    Table createTable(String material, int height, int width, int lenght);
}
