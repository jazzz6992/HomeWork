package task3;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Rectangle(2, 3);
        shapes[1] = new Square(3);
        shapes[2] = new Circle(3);
        shapes[3] = new Triangle(3, 4, 5);
        for (int i = 0; i < shapes.length; i++) {
            System.out.println(shapes[i]);
        }
        ((Rectangle) shapes[0]).setA(1);
        ((Rectangle) shapes[0]).setB(2);
        ((Square) shapes[1]).setA(1);
        ((Circle) shapes[2]).setR(1);
        ((Triangle)shapes[3]).setA(1);
        ((Triangle)shapes[3]).setC(1);
        for (int i = 0; i < shapes.length; i++) {
            System.out.println(shapes[i]);
        }
    }
}
