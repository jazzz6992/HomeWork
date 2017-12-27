package manager.search;

import entity.Stock;
import manager.listeners.SearchCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class Searcher implements Runnable {

    private SearchCompleteListener listener;
    private String key;
    private List<Stock> stocks;

    public Searcher(SearchCompleteListener listener, String key, List<Stock> stocks) {
        this.listener = listener;
        this.key = key;
        this.stocks = stocks;
    }

    @Override
    public void run() {
        synchronized (listener) {
            List<Stock> result = new ArrayList<>();
            for (Stock s :
                    stocks) {
                if (s.getName().toLowerCase().contains(key.toLowerCase())) {
                    result.add(s);
                }
            }
            listener.onSearchSuccess(result);
        }
    }
}

