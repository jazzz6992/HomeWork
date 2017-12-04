package l7;

public class Main {
    public static void main(String[] args) {
        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.err.println("Division by zero");
        }
        try {
            System.out.println(test(5, 5));
        } catch (ArithmeticException e) {
            System.err.println("exc");
        } catch (FiveExc e) {
            System.err.println("FIVE");
        }

    }

    public static int test(int a, int b) throws FiveExc {
        if (b == 5) {
            throw new FiveExc();
        }
        return a / b;
    }
}
