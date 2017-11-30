package task1;

public class Denomination {
    private int denomination;
    private int amount;

    public Denomination(int denomination, int amount) {
        this.denomination = denomination;
        this.amount = amount;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
