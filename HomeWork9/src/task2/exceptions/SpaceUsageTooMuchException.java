package task2.exceptions;

public class SpaceUsageTooMuchException extends Exception {
    private int squareUsageDesired;
    private double squareUsageAvailaible;

    public SpaceUsageTooMuchException(int squareUsageDesired, double squareUsageAvailaible) {
        this.squareUsageDesired = squareUsageDesired;
        this.squareUsageAvailaible = squareUsageAvailaible;
    }

    @Override
    public String getMessage() {
        return String.format("Попытка использовать %d м2 из %.2f м2 доступных к использованию", squareUsageDesired, squareUsageAvailaible);
    }
}