package task1;

import task1.Functions.BankName;
import task1.Functions.DenominationsAmountInformation;
import task1.Functions.FabricantName;

import java.util.List;

public class AtmWithBlackJackAndHookers extends Atm implements BankName, FabricantName, DenominationsAmountInformation {

    private String bankName;
    private String fabricantName;

    public AtmWithBlackJackAndHookers(List<Integer> denominations, int amount, String bankName, String fabricantName) {
        super(denominations, amount, true);
        this.bankName = bankName;
        this.fabricantName = fabricantName;
    }

    @Override
    public String getBankName() {
        return bankName;
    }


    @Override
    public String getFabricantName() {
        return fabricantName;
    }

    @Override
    public void printTotal() {
        System.out.println("Количество денег в банкомате = " + getTotal());
        printDenominationsAmount();
    }

    @Override
    public void printDenominationsAmount() {
        System.out.println("Оставштеся в банкомате банкноты: ");
        for (int i = 0; i < getDenominations().size(); i++) {
            System.out.println(getDenominations().get(i) + " " + getAmounts().get(i));
        }
    }
}
