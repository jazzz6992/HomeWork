package task3;

public class Square extends Rectangle {

    public Square(double a) {
        super(a, a);
    }

    @Override
    public void setA(double a) {
        super.setA(a);
        super.setB(a);
    }

    @Override
    public void setB(double b) {
        super.setB(b);
        super.setA(b);
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().indexOf("b=") - 2).replaceAll("Rectangle", "Square") + "}";
    }
}
