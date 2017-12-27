package manager.listeners;

import entity.Stock;

import java.util.List;

public interface SortCompleteListener {
    void onSortSuccess(List<Stock> stocks);
}
