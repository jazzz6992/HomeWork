package task1;

import task1.Functions.Depositable;

import java.util.ArrayList;
import java.util.List;

public class Depositor implements Depositable {
    private Atm atm;

    public Depositor(Atm atm) {
        this.atm = atm;
    }

    @Override
    public boolean deposit(int amount) {
        int amountTmp = amount;
        List<Integer> ammountsTmp = new ArrayList<>(atm.getAmounts());
        for (int i = 0; i < atm.getDenominations().size(); i++) {
            if (ammountsTmp.size() < atm.getDenominations().size()) {
                ammountsTmp.add(amountTmp / atm.getDenominations().get(i));
            } else {
                ammountsTmp.set(i, ammountsTmp.get(i) + amountTmp / atm.getDenominations().get(i));
            }
            amountTmp %= atm.getDenominations().get(i);
        }
        if (amountTmp != 0) {
            System.out.println("Неудача");
            return false;
        } else {
            atm.setTotal(atm.getTotal() + amount);
            atm.setAmounts(ammountsTmp);
            System.out.println("Пополнение произведено");
            return true;
        }
    }
}
