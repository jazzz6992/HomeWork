package task3;

public class Rectangle extends Shape {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        super(a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    public double countPerimeter(double... dem) {
        return (dem[0] + dem[1]) * 2;
    }

    @Override
    public double countSquare(double... dem) {
        return dem[0] * dem[1];
    }
}
