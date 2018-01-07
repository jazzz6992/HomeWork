package task2.exceptions;

public class IlluminanceTooLittleException extends WrongLightException {

    public IlluminanceTooLittleException(int min, int max, int actual) {
        super(min, max, actual);
    }
}
