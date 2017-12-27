package ui;

import entity.Stock;
import entity.StockExchange;
import manager.Manager;

import java.util.List;

public interface Ui {
    void setManager(Manager manager);

    void print(String s);

    void print(StockExchange stockExchange);

    void print(List<Stock> list);
}
