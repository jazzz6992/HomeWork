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

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
        setOneDem(0, r);
    }

    @Override
    public String toString() {
        return super.toString() +
                "r=" + r +
                '}';
    }
}
