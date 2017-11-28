package l5;

public class Button {
    private OnClickListener clickListener;

    public void setOnClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void emulateClickButton() {
        if (clickListener != null) {
            clickListener.onClick();
        }
    }
}
