package task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Gadget[] gadgets = new Gadget[3];
        gadgets[0] = new Refrigirator();
        gadgets[1] = new HiFi(true, HiFi.Source.CD);
        gadgets[2] = new TVSet(false);
        for (Gadget gadget : gadgets) {
            System.out.println("****************");
            gadget.setOn(true);
            System.out.println("****************");
        }
        Thread.sleep(3000);
        for (Gadget gadget : gadgets) {
            System.out.println("****************");
            gadget.setOn(false);
            System.out.println("****************");
        }
    }
}
