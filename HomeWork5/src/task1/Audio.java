package task1;

public abstract class Audio extends Gadget {
    private byte vol;
    private boolean isStereo;
    private boolean playPause;

    public Audio(boolean stereo) {
        this.vol = 0;
        this.isStereo = stereo;
        playPause = false;
    }

    public byte getVol() {
        return vol;
    }

    public void setVol(byte vol) {
        this.vol = vol;
    }

    public boolean isStereo() {
        return isStereo;
    }

    public void setStereo(boolean stereo) {
        this.isStereo = stereo;
    }


    @Override
    public void doAction() {
        if (isOn()) {
            System.out.println("Начато воспроизведение звука");
            System.out.println("Stereo = " + isStereo);
        }
    }
}
