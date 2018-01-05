package manager;

import entity.Stock;
import entity.StockExchange;
import manager.buttonChoices.Action;
import manager.buttonChoices.Source;
import manager.download.Downloader;
import manager.listeners.DownloadCompleteListener;
import manager.listeners.ParseCompleteListener;
import manager.listeners.SearchCompleteListener;
import manager.listeners.SortCompleteListener;
import manager.parse.AbstractParser;
import manager.parse.JsonParser;
import manager.parse.XmlParser;
import manager.search.Searcher;
import manager.sort.Sorter;
import ui.Ui;

import java.io.File;
import java.util.List;

public class Manager implements DownloadCompleteListener, ParseCompleteListener, SortCompleteListener, SearchCompleteListener {
    private File file;
    private StockExchange stockExchange;
    private Ui ui;
    private List<Stock> stocksToDisplay;
    public static final String JSON_LINK = "http://kiparo.ru/t/stock.json";
    public static final String XML_LINK = "http://kiparo.ru/t/stock.xml";

    public Manager(Ui ui) {
        this.ui = ui;
    }

    public void getFile(Source source) {
        String link;
        String fileName;
        if (source == Source.JSON) {
            link = JSON_LINK;
            fileName = "src/content.json";
        } else {
            link = XML_LINK;
            fileName = "src/content.xml";
        }
        Downloader downloader = new Downloader(link, fileName, this);
        Thread downloadThread = new Thread(downloader, "download thread");
        downloadThread.start();
    }

    public void parse() {
        AbstractParser parser;
        if (file.getName().toLowerCase().endsWith("xml")) {
            parser = new XmlParser(this, file);
        } else {
            parser = new JsonParser(this, file);
        }
        Thread parseThread = new Thread(parser);
        parseThread.start();
    }

    @Override
    public void onParseSuccess(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
        stocksToDisplay = stockExchange.getStock();
        showAll();
        synchronized (this) {
            this.notifyAll();
        }
    }

    @Override
    public void onParseFailed() {
        ui.print("Parse failed");
    }

    @Override
    public void onDownloadSuccess(File file) {
        this.file = file;
        parse();
    }

    @Override
    public void onDownloadFailed(String message) {
        ui.print("Failed");
    }

    public void sort(Action action) {
        synchronized (this) {
            if (stockExchange == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Thread sortThread = new Thread(new Sorter(this, stocksToDisplay, action));
        sortThread.start();
    }

    public void search(String key) {
        if (key != null && !key.equals("")) {
            Thread searchThread = new Thread(new Searcher(this, key, stocksToDisplay));
            searchThread.start();
        }
    }

    @Override
    public void onSortSuccess(List<Stock> stocks) {
        stocksToDisplay = stocks;
        ui.print(stocksToDisplay);
    }

    @Override
    public void onSearchSuccess(List<Stock> stocks) {
        stocksToDisplay = stocks;
        ui.print(stocks);
    }

    public void showAll() {
        if (stockExchange != null) {
            stocksToDisplay = stockExchange.getStock();
            ui.print(stockExchange);
        }
    }

    public void resetAll() {
        synchronized (this) {
            file.delete();
            file = null;
            stockExchange = null;
            stocksToDisplay = null;
        }
    }
}
