package manager.listeners;

import model.entity.StockExchange;

public interface ParseCompleteListener {
    void onParseSuccess(StockExchange stockExchange);

    void onParseFailed(String message);
}
