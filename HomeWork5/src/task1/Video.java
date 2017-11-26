package task1;

public abstract class Video extends Audio {
    private byte brightness;
    private byte color;
    private byte contrast;

    public Video(boolean stereo) {
        super(stereo);
        this.brightness = 0;
        this.color = 0;
        this.contrast = 0;
    }

    public byte getBrightness() {
        return brightness;
    }

    public void setBrightness(byte brightness) {
        this.brightness = brightness;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }

    public byte getContrast() {
        return contrast;
    }

    public void setContrast(byte contrast) {
        this.contrast = contrast;
    }

    @Override
    public void doAction() {
        super.doAction();
        if (isOn()) {
            System.out.println("Начато воспроизведение видео");
        }
    }
}
