package manager.listeners;

import entity.Stock;

import java.util.List;

public interface SearchCompleteListener {
    void onSearchSuccess(List<Stock> stocks);

    void onSearchFailed();
}
