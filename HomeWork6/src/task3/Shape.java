package task3;

public abstract class Shape {

    private double perimeter;
    private double square;
    private double[] demensions;

    public Shape(double... dem) {
        demensions = dem;
        setPerimeter();
        setSquare();
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getSquare() {
        return square;
    }

    public abstract double countPerimeter(double... dem);

    public abstract double countSquare(double... dem);

    public void setPerimeter() {
        perimeter = countPerimeter(demensions);
    }

    public void setSquare() {
        square = countSquare(demensions);
    }

    public double[] getDemensions() {
        return demensions;
    }

    public void setOneDem(int i, double d) {
        demensions[i] = d;
        setPerimeter();
        setSquare();
    }

    @Override
    public String toString() {
        return "Shape {" + getClass().getSimpleName() +
                " perimeter=" + perimeter +
                ", square=" + square +
                ", ";
    }
}
