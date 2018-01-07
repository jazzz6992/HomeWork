package task3.atm;



import task3.atm.functions.BankName;
import task3.atm.functions.DenominationsAmountInformation;
import task3.atm.functions.FabricantName;

import java.util.List;

public class AtmWithBlackJackAndHookers extends Atm implements BankName, FabricantName, DenominationsAmountInformation {

    private String bankName;
    private String fabricantName;

    public AtmWithBlackJackAndHookers(List<Denomination> denominations, boolean depositable, String bankName, String fabricantName) {
        super(denominations, depositable);
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
            System.out.println(getDenominations().get(i).getDenomination() + " " + getDenominations().get(i).getAmount());
        }
    }
}
