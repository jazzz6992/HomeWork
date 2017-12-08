package task1.exceptions;

public class WrongOperatorException extends Exception implements RussianMessage {

    private String russianMessage;

    public String getRussianMessage() {
        return russianMessage;
    }

    public WrongOperatorException() {
    }

    public WrongOperatorException(String message) {
        russianMessage = message;
    }
}
