package model;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    public List<Stock> getStocksToDisplay() {
        return stocksToDisplay;
    }

    public void setStocksToDisplay(List<Stock> stocksToDisplay) {
        this.stocksToDisplay = stocksToDisplay;
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
