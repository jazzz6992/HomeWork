package task1;

public class HiFi extends Audio {


    public enum Source {
        CD, TAPE, TUNER, AUX
    }

    private Source source;

    public HiFi(boolean stereo, Source source) {
        super(stereo);
        this.source = source;
    }

    @Override
    public void doAction() {
        super.doAction();
        if (isOn()) {
            System.out.println("Источник " + source.name());
        }
    }
}
