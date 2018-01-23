package manager.interfaces;

import model.entity.Stock;

import java.util.List;

//интерфейс для отслеживания изменений в списке акций для отображения
public interface ListForPrintChangeListener {
    void onListChanged(List<Stock> stocks);
}
