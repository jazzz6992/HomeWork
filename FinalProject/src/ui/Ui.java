package ui;

import entity.Stock;
import entity.StockExchange;

import java.util.List;

public interface Ui {

    void print(String s);

    void print(StockExchange stockExchange);

    void print(List<Stock> list);
}
