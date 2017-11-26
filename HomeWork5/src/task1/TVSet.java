package task1;

public class TVSet extends Video {
   private byte chanel;

    public TVSet(boolean stereo) {
        super(stereo);
        this.chanel = 0;
    }

    public byte getChanel() {
        return chanel;
    }

    public void setChanel(byte chanel) {
        if (chanel >= 0) {
            this.chanel = chanel;
        }
    }

    @Override
    public void doAction() {
        super.doAction();
        if (isOn()) {
            System.out.println("Канал " + chanel);
        }
    }
}
