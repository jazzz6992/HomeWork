package task2.exceptions;

public class IlluminanceTooMuchException extends WrongLightException {

    public IlluminanceTooMuchException(int min, int max, int actual) {
        super(min, max, actual);
    }
}
