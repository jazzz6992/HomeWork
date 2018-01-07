package task1;

import java.util.List;

public class AtmSimple extends Atm {
    public AtmSimple(List<Denomination> denominations) {
        super(denominations, false);
    }

    @Override
    public void printTotal() {
        System.out.println("ваш баланс состовляет " + getTotal());
    }
}
