package task1.AbstractFactory;

public class Main {
    public static void main(String[] args) {
        FurnitureFactory furnitureFactory = new ClassicFurnitureFactory();
        System.out.println(furnitureFactory.createChair("wood", 1, 1, 1).getClass().getSimpleName());
        System.out.println(furnitureFactory.createTable("wood", 1, 2, 2).getClass().getSimpleName());
        furnitureFactory = new ModernFurnitureFactory();
        System.out.println();
        System.out.println(furnitureFactory.createChair("metal", 1, 1, 1).getClass().getSimpleName());
        System.out.println(furnitureFactory.createTable("metal", 1, 2, 2).getClass().getSimpleName());
    }
}
