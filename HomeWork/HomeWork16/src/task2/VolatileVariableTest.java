package task2;

public class VolatileVariableTest implements Runnable {
    private volatile boolean isCancel = false;

    public void cancel() {
        this.isCancel = true;
    }

    public void run() {
        while (!this.isCancel) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tik");
        }
    }
}
