package l4;

public abstract class Shape {
    private double S;
    private double diameter;

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public abstract double getSqr();
}
