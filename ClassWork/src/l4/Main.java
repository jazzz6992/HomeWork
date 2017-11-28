package l4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Shape shape = null;
        switch (new BufferedReader(new InputStreamReader(System.in)).readLine()) {
            case "a":
                shape = new Rectangle(2, 3);
                break;
            case "b":
                shape = new Treangle(2, 2, 3);
                break;
            case "c":
                shape = new Circle(3);
                break;
        }
        printShapeSqr(shape);
    }

    private static void printShapeSqr(Shape shape) {
        System.out.println(shape.getSqr());
    }
}
