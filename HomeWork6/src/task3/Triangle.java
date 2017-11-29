package task3;

public class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        super(a, b, c);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double countPerimeter(double... dem) {
        return dem[0] + dem[1] + dem[2];
    }

    @Override
    public double countSquare(double... dem) {
        double p = (dem[0] + dem[1] + dem[2]) / 2;
        return Math.sqrt(p * (p - dem[0]) * (p - dem[1]) * (p - dem[2]));
    }
}
