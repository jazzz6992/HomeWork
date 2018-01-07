package task1.exceptions;

public class NumberParseException extends NumberFormatException implements RussianMessage {
    private String russianMessage;

    public String getRussianMessage() {
        return russianMessage;
    }

    public NumberParseException() {
    }

    public NumberParseException(String s) {
        russianMessage = s;
    }
}
