package task1.AbstractFactory.furniture;

public abstract class Furniture {
    private String material;
    private int height;
    private int width;
    private int lenght;

    public Furniture(String material, int height, int width, int lenght) {
        this.material = material;
        this.height = height;
        this.width = width;
        this.lenght = lenght;
    }
}
