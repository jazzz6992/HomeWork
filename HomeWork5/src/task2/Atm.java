package task2;

import java.util.*;

public class Atm {
    private List<Integer> denominations = new ArrayList<>();
    private List<Integer> amounts = new ArrayList<>();
    private int total;

    public List<Integer> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Integer> denominations) {
        this.denominations = denominations;
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public int getTotal() {
        return total;
    }

    public Atm(int amount) {
        denominations.add(50);
        denominations.add(20);
        denominations.add(100);
        Comparator comparator = Collections.reverseOrder();
        denominations.sort(comparator);
        deposit(amount);
    }

    public boolean deposit(int amount) {
        int amountTmp = amount;
        for (Integer i :
                denominations) {
            amounts.add(amountTmp / i);
            amountTmp %= i;
        }
        if (amountTmp != 0) {
            for (int i = 0; i < amounts.size(); i++) {
                amounts.set(i, 0);
            }
            total = 0;
            System.out.println("Неудача");
            printTotal();
            return false;
        } else {
            total = amount;
            System.out.println("Пополнение произведено");
            printTotal();
            return true;
        }
    }

    public boolean withdraw(int amount) {
        if (total < amount) {
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

    public void printTotal() {
        System.out.println("Количество денег в банкомате = " + total);
        System.out.println("Оставштеся в банкомате банкноты: ");
        for (int i = 0; i < denominations.size(); i++) {
            System.out.println(denominations.get(i) + " " + amounts.get(i));
        }
    }

    public static void main(String[] args) {
        Atm a = new Atm(390);
        a.withdraw(120);
    }
}
