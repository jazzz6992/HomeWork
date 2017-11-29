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

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
        setOneDem(0, a);
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
        setOneDem(1, b);
    }

    @Override
    public String toString() {
        return super.toString() +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
