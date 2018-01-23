package ui.interfaces;

public interface Ui {

    enum Action {
        SORT_BY_PRICE, SHOW_ALL, SORT_BY_NAME, BACK, SEARCH, AVERAGE
    }

    enum Source {
        XML, JSON
    }

    void initializeUi();

    void print(String s);
}
