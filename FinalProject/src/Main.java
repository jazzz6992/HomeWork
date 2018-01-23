import ui.UiImplimentation;
import ui.interfaces.Ui;

public class Main {
    public static void main(String[] args) {
        Ui ui = new UiImplimentation();
        ui.initializeUi();
    }
}
