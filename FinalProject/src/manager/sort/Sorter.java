package manager.sort;

import manager.buttonChoices.Action;
import manager.listeners.ListForPrintChangeListener;
import model.Model;
import model.entity.Stock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter implements Runnable {
    private ListForPrintChangeListener listener;
    private final Model model;
    private Comparator<Stock> comparator;

    /*
    при создании инициализирует компаратор нужным, в зависимости от выбора пользователя и
    далее использует его
     */

    public Sorter(ListForPrintChangeListener listener, Model model, Action action) {
        this.listener = listener;
        this.model = model;
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
        /*
        в случае, если данные еще не готовы, ждет оповещения.
        затем ищет соответствующие ключу акции из списка для отображения, формирует из них новый
        список и оповещает слушателя по готовности
         */
        synchronized (model) {
            while (model.getStocksToDisplay() == null) {
                try {
                    model.wait();
                } catch (InterruptedException e) {
                }
            }
            List<Stock> stocks = new ArrayList<>(model.getStocksToDisplay());
            stocks.sort(comparator);
            listener.onListForPrintChanged(stocks);
        }
    }
}
