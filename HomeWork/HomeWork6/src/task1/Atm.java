package task1;

import task1.functions.BalanceInformation;
import task1.functions.Depositable;
import task1.functions.Withdrawable;

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
        List<Integer> availaible = new LinkedList<>();
        for (int i = 0; i < denominations.size(); i++) {
            for (int j = 0; j < denominations.get(i).getAmount(); j++) {
                availaible.add(denominations.get(i).getDenomination());
            }
        }
        Map<Integer, Integer> result = new HashMap<>();
        withdraw(amount, availaible, 0, result);
        if (result.size() != 0) {
            System.out.println("Withdraw:\n");
            for (Map.Entry<Integer, Integer> entry :
                    result.entrySet()) {
                System.out.println("Denomination = " + entry.getKey() + " amount = " + entry.getValue() + "\n");
                for (Denomination d :
                        denominations) {
                    if (d.getDenomination() == entry.getKey()) {
                        d.setAmount(d.getAmount() - entry.getValue());
                        break;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private Map<Integer, Integer> withdraw(int amount, List<Integer> availaible, int count, Map<Integer, Integer> map) {
        for (int i = count; i < availaible.size(); i++) {
            if (amount < availaible.get(i)) {
                continue;
            } else if (amount > availaible.get(i)) {
                withdraw(amount - availaible.get(i), availaible, count + 1, map);
                if (map.size() != 0) {
                    if (map.containsKey(availaible.get(i))) {
                        map.put(availaible.get(i), map.get(availaible.get(i)) + 1);
                        return map;
                    } else {
                        map.put(availaible.get(i), 1);
                        return map;
                    }
                }
            } else {
                map.put(availaible.get(i), 1);
                return map;
            }
        }
        return map;
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

    public void deposit(int denom, int amount) {
        if (depositor != null) {
            depositor.deposit(denom, amount);
        } else {
            System.out.println("Банкомат не поддерживает пополнение");
        }
    }

    public void setDepositable(Depositable depositor) {
        this.depositor = depositor;
    }

    public class Depositor implements Depositable {


        public Depositor() {

        }

        @Override
        public void deposit(int denom, int amount) {
            for (Denomination d :
                    denominations) {
                if (d.getDenomination() == denom) {
                    d.setAmount(d.getAmount() + amount);
                    return;
                }
            }
            Denomination denomination = new Denomination(denom, amount);
            denominations.add(denomination);
        }
    }
}
