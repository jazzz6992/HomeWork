package manager.parse;

import entity.StockExchange;

public interface ParseCompleteListener {
    void onParseSuccess(StockExchange stockExchange);

    void onParseFailed();
}
