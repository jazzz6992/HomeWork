package task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Button button  = new Button();
        button.setOnClickListener(new UI());
        Thread.sleep(5000);
        button.emulateClickButton();
        button.setOnClickListener(new UI2());
        Thread.sleep(5000);
        button.emulateClickButton();
    }
}
