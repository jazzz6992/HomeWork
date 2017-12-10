package task2;

public class Furniture {
    private String name;
    private int square;

    public Furniture(String name, int square) {
        this.name = name;
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public String describe() {
        return String.format("          %s (площадь = %d м2)", name, square);
    }
}
