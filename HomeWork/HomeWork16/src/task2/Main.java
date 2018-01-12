package task2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        VolatileVariableTest test = new VolatileVariableTest();
        Thread testThread1 = new Thread(test);
        testThread1.start();
        Thread.sleep(10000);
        test.cancel();
    }
}
