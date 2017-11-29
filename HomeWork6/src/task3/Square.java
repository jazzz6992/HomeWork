package task3;

public class Square extends Rectangle {

    public Square(double a) {
        super(a, a);
    }

    @Override
    public double countPerimeter(double... dem) {
        return dem[0] * 4;
    }

    @Override
    public double countSquare(double... dem) {
        return dem[0] * dem[0];
    }
}
