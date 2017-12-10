package task2.exceptions;

public class WrongLightException extends Exception {
    private int min;
    private int max;
    private int actual;

    public WrongLightException(int min, int max, int actual) {
        this.min = min;
        this.max = max;
        this.actual = actual;
    }

    @Override
    public String getMessage() {
        return "Требуемое освещение должно находиться в пределах от " + min + " до " + max + ". Запрашиваемое освещение составляет " + actual;
    }
}