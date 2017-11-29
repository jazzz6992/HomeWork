package task3;

public abstract class Shape {

    private double perimeter;
    private double square;

    public Shape(double... dem) {
       perimeter =  countPerimeter(dem);
       square = countSquare(dem);
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getSquare() {
        return square;
    }

    public abstract double countPerimeter(double... dem);

    public abstract double countSquare(double... dem);
}
