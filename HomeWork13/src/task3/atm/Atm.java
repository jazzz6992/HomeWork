package task3.atm;


import task3.atm.functions.BalanceInformation;
import task3.atm.functions.Depositable;
import task3.atm.functions.Withdrawable;

import java.util.*;

public abstract class Atm implements Withdrawable, BalanceInformation, Depositable {
    private List<Denomination> denominations;
    private int total;
    private Depositable depositor;

    public Atm(List<Denomination> denominations, boolean depositable) {
        setDenominations(denominations);
        if (depositable) {
            depositor = new Depositor();
        }
    }

    public boolean withdraw(int amount) {
        if (total < amount) {
            System.out.println("Нехватает денег");
            return false;
        }
        int tmpAmount = amount;
        List<Denomination> tmp = AtmHelper.cloneDenominations(denominations);
        Map<Integer, Integer> toPrint = new HashMap<>();
        for (int i = 0; i < denominations.size(); i++) {
            if (denominations.get(i).getDenomination() > tmpAmount) {
                continue;
            }
            int curAmount = tmp.get(i).getAmount();
            if (curAmount <= 0) {
                continue;
            } else {
                int preferedAmount = tmpAmount / denominations.get(i).getDenomination();
                if (preferedAmount > curAmount) {
                    tmpAmount -= curAmount * denominations.get(i).getDenomination();
                    tmp.get(i).setAmount(0);
                    toPrint.put(denominations.get(i).getDenomination(), curAmount);
                } else {
                    tmpAmount -= preferedAmount * denominations.get(i).getDenomination();
                    tmp.get(i).setAmount(curAmount - preferedAmount);
                    toPrint.put(denominations.get(i).getDenomination(), preferedAmount);
                }
            }
        }
        if (tmpAmount != 0) {
            System.out.println("Ошибка снятия");
            printTotal();
            return false;
        } else {
            System.out.println("Снятие произведено");
            denominations = tmp;
            total -= amount;
            printTotal();
            System.out.println("Выдано купюр:");
            for (Map.Entry<Integer, Integer> e :
                    toPrint.entrySet()) {
                System.out.println("Номинал:" + e.getKey() + " - Кол-во: " + e.getValue());
            }
            return true;
        }
    }

    public abstract void printTotal();

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public List<Denomination> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Denomination> denominations) {
        total = 0;
        Comparator<Denomination> comparator = new Comparator<Denomination>() {
            @Override
            public int compare(Denomination o1, Denomination o2) {
                return Integer.compare(o1.getDenomination(), o2.getDenomination());
            }
        };
        denominations.sort(comparator);
        Collections.reverse(denominations);
        for (Denomination d :
                denominations) {
            total += d.getDenomination() * d.getAmount();
        }
        this.denominations = denominations;
    }

    public boolean deposit(int amount) {
        if (depositor != null) {
            return depositor.deposit(amount);
        } else {
            System.out.println("Банкомат не поддерживает пополнение");
            return false;
        }
    }

    public void setDepositable(Depositable depositor) {
        this.depositor = depositor;
    }

    public class Depositor implements Depositable {


        public Depositor() {

        }

        @Override
        public boolean deposit(int amount) {
            if (amount<0) {
                return false;
            }
            int amountTmp = amount;
            List<Denomination> denominationsTmp = AtmHelper.cloneDenominations(denominations);
            for (int i = 0; i < denominationsTmp.size(); i++) {
                denominationsTmp.get(i).setAmount(denominationsTmp.get(i).getAmount() + amountTmp / denominationsTmp.get(i).getDenomination());
                amountTmp %= denominationsTmp.get(i).getDenomination();
            }
            if (amountTmp != 0) {
                System.out.println("Неудача");
                return false;
            } else {
                total += amount;
                denominations = denominationsTmp;
                System.out.println("Пополнение произведено");
                return true;
            }
        }
    }
}
