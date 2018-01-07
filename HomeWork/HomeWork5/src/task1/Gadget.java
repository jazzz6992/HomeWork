package task1;

public abstract class Gadget {
    private boolean isOn;

    public Gadget() {
        this.isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
        if (on) {
            System.out.println( getClass().getSimpleName() + " включен");
        } else {
            System.out.println( getClass().getSimpleName() + " выключен");
        }
        doAction();
    }

    public abstract void doAction();
}
