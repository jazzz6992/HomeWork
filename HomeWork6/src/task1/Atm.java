package task1;

import task1.Functions.BalanceInformation;
import task1.Functions.Depositable;
import task1.Functions.Withdrawable;

import java.util.*;

public abstract class Atm implements Withdrawable, BalanceInformation, Depositable {
    private List<Integer> denominations;
    private List<Integer> amounts = new ArrayList<>();
    private int total;
    private boolean depositable;
    private Depositor depositor;

    public Atm(List<Integer> denominations, int amount, boolean depositable) {
        this.denominations = denominations;
        Comparator<Integer> comparator = Collections.reverseOrder();
        denominations.sort(comparator);
        depositor = new Depositor(this);
        deposit(amount);
        if (!depositable) {
            depositor = null;
        }
    }

    public boolean deposit(int amount) {
        if (depositor != null) {
            return depositor.deposit(amount);
        } else {
            System.out.println("Этот банкомат не поддерживает пополнение");
            return false;
        }
    }

    public boolean withdraw(int amount) {
        if (total < amount) {
            System.out.println("Нехватает денег");
            return false;
        }
        int tmpAmount = amount;
        List<Integer> tmp = new ArrayList<>(amounts);
        Map<Integer, Integer> toPrint = new HashMap<>();
        for (int i = 0; i < denominations.size(); i++) {
            if (denominations.get(i) > tmpAmount) {
                continue;
            }
            int curAmount = tmp.get(i);
            if (curAmount <= 0) {
                continue;
            } else {
                int preferedAmount = tmpAmount / denominations.get(i);
                if (preferedAmount > curAmount) {
                    tmpAmount -= curAmount * denominations.get(i);
                    tmp.set(i, 0);
                    toPrint.put(denominations.get(i), curAmount);
                } else {
                    tmpAmount -= preferedAmount * denominations.get(i);
                    tmp.set(i, curAmount - preferedAmount);
                    toPrint.put(denominations.get(i), preferedAmount);
                }
            }
        }
        if (tmpAmount != 0) {
            System.out.println("Ошибка снятия");
            printTotal();
            return false;
        } else {
            System.out.println("Снятие произведено");
            amounts = tmp;
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

    public List<Integer> getDenominations() {
        return denominations;
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }

    public void setDenominations(List<Integer> denominations) {
        this.denominations = denominations;
    }
}
