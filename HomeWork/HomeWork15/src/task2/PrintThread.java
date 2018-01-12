package task2;

public class PrintThread extends Thread {
    private int counter = 1;
    Main main;

    public PrintThread(String name, Main main) {
        super(name);
        this.main = main;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            main.print10(counter);
            counter += 10;
        }
    }
}
