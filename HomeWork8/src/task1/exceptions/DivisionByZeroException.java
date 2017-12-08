package task1.exceptions;

public class DivisionByZeroException extends ArithmeticException implements RussianMessage {
    private String russianMessage;
    public String getRussianMessage() {
        return russianMessage;
    }

    public DivisionByZeroException() {
    }

    public DivisionByZeroException(String s) {
        russianMessage = s;
    }
}
