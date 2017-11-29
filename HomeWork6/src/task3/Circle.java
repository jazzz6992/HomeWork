package task3;

public class Circle extends Shape {
   private double r;

    public Circle(double r) {
        super(r);
        this.r = r;
    }

    @Override
    public double countPerimeter(double... dem) {
        return 2 * Math.PI * dem[0];
    }

    @Override
    public double countSquare(double... dem) {
        return Math.PI * Math.pow(dem[0], 2);
    }
}
