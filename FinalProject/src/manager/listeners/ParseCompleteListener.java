package manager.listeners;

import model.entity.StockExchange;

//интерфейс для отслеживания результата парсинга
public interface ParseCompleteListener {
    void onParseSuccess(StockExchange stockExchange);

    void onParseFailed(String message);
}
