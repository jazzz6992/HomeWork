package model;

import manager.interfaces.DataChangedResultListener;
import model.entity.Stock;
import model.entity.StockExchange;

import java.io.File;
import java.util.List;

public class Model {

    //файл с данными
    private File file;
    //распаршеный объект из файла
    private StockExchange stockExchange;
    //список акций для показа на текущий момент
    private List<Stock> stocksToDisplay;
    private DataChangedResultListener listener;

    public Model(DataChangedResultListener listener) {
        this.listener = listener;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    //устанавливает данные модели и оповещает потоки, которые, возможно, ожидают эти данные
    public synchronized void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
        if (stockExchange != null) {
            setStocksToDisplay(stockExchange.getStock());
        }
        notifyAll();
    }

    public List<Stock> getStocksToDisplay() {
        return stocksToDisplay;

    }

    //устанавливает данные модели и оповещает об этом слушателя и ожидающие потоки
    public synchronized void setStocksToDisplay(List<Stock> stocksToDisplay) {
        this.stocksToDisplay = stocksToDisplay;
        if (stocksToDisplay != null) {
            listener.onDataChanged();
        }
    }

    /*
      берет из stockExchange только информацию о самой бирже.
      информацию об акциях берет из списка на печать, т.к. при поиске и сортировке
      этот список будет меняться
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(stockExchange.toString());
        for (Stock s :
                stocksToDisplay) {
            sb.append(s.toString());
        }
        return sb.toString();
    }
}
