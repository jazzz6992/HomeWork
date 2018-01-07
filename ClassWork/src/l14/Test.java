package l14;

public class Test implements Runnable {
    public int publicInt;
    private int privateInt;

    public void publicMethod() {
        System.out.println("inside method");
    }

    private void privateMethod() {
        System.out.println("inside private method");
    }


    @Override
    public void run() {

    }
}
