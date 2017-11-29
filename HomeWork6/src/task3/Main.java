package task3;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Rectangle(2, 3);
        shapes[1] = new Square(3);
        shapes[2] = new Circle(3);
        shapes[3] = new Triangle(3, 4, 5);
        for (int i = 0; i < shapes.length; i++) {
            System.out.println("Финура " + i);
            System.out.println(shapes[i].getSquare());
            System.out.println(shapes[i].getPerimeter());
            System.out.println();
        }
    }
}
