package manager.sort;

import entity.Stock;
import manager.buttonChoices.Action;
import manager.listeners.SortCompleteListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter implements Runnable {
    private SortCompleteListener listener;
    private List<Stock> stocks;
    private Comparator<Stock> comparator;

    public Sorter(SortCompleteListener listener, List<Stock> stocks, Action action) {
        this.listener = listener;
        this.stocks = new ArrayList<>(stocks);
        switch (action) {
            case SORT_BY_NAME:
                comparator = new Comparator<Stock>() {
                    @Override
                    public int compare(Stock o1, Stock o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                };
                break;
            case SORT_BY_PRICE:
                comparator = new Comparator<Stock>() {
                    @Override
                    public int compare(Stock o1, Stock o2) {
                        return Double.compare(o2.getBid(), o1.getBid());
                    }
                };
                break;
        }
    }


    @Override
    public void run() {
        stocks.sort(comparator);
        listener.onSortSuccess(stocks);
    }
}
