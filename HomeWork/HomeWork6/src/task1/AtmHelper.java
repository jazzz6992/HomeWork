package task1;


import java.util.ArrayList;
import java.util.List;

public class AtmHelper {
    public static List<Denomination> cloneDenominations(List<Denomination> list) {
        List<Denomination> clone = new ArrayList<>();
        for (Denomination d :
                list) {
            Denomination copy = new Denomination(d.getDenomination(), d.getAmount());
            clone.add(copy);
        }
        return clone;
    }
}
