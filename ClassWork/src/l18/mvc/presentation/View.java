package l18.mvc.presentation;

public class View {
    public static void main(String[] args) {
        View view = new View();
        view.startUI();
    }

    private Controller controller;

    public View() {
        controller = new Controller(this);
    }

    private void startUI() {
        System.out.println("Sup, bitch?!");
        controller.getMoney();
    }

    public void showGetMoneyResult(boolean isOk) {
        if (isOk) {
            System.out.println("ok");
        } else {
            System.out.println("not ok");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
