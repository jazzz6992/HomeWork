package model;

import manager.interfaces.DataChangedResultListener;
import model.download.Downloader;
import model.entity.Stock;
import model.entity.StockExchange;
import model.interfaces.DownloadCompleteListener;
import model.interfaces.ParseCompleteListener;
import model.parse.AbstractParser;
import model.parse.SimpleParserFactory;

import java.io.File;
import java.util.List;

public class Model implements DownloadCompleteListener, ParseCompleteListener {

    //файл с данными
    private File file;
    //распаршеный объект из файла
    private StockExchange stockExchange;
    //список акций для показа на текущий момент
    private List<Stock> stocksToDisplay;
    private DataChangedResultListener listener;

    public Model(DataChangedResultListener listener) {
        this.listener = listener;
    }

    public void getData(String url, String path) {
        downloadData(url, path);
    }

    //создает загрузчик и запускает на его основе новый поток
    private void downloadData(String url, String path) {
        Downloader downloader = new Downloader(url, path, this, this);
        Thread downloadThread = new Thread(downloader, "download thread");
        downloadThread.start();
    }

    @Override
    public void onDownloadSuccess(File file) {
        this.file = file;
        parseData();
    }

    @Override
    public void onDownloadFailed(String message) {
        listener.onFail(message);
    }

    //создает нужный парсер на основе данных модели и запускает на его основе новый поток
    private void parseData() {
        AbstractParser parser;
        parser = SimpleParserFactory.createParser(this, this);
        Thread parseThread = new Thread(parser, "parse thread");
        parseThread.start();
    }

    @Override
    public void onParseSuccess(StockExchange stockExchange) {
        setStockExchange(stockExchange);
    }

    @Override
    public void onParseFailed(String message) {
        listener.onFail(message);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    //устанавливает данные модели и оповещает потоки, которые, возможно, ожидают эти данные
    public synchronized void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
        if (stockExchange != null) {
            setStocksToDisplay(stockExchange.getStock());
        }
        notifyAll();
    }

    public List<Stock> getStocksToDisplay() {
        return stocksToDisplay;

    }

    //устанавливает данные модели и оповещает об этом слушателя и ожидающие потоки
    public synchronized void setStocksToDisplay(List<Stock> stocksToDisplay) {
        this.stocksToDisplay = stocksToDisplay;
        if (stocksToDisplay != null) {
            listener.onSuccess();
        }
    }

    /*
    обнуляет поля модели и удаляет загруженный файл
     */
    public void clear() {
        synchronized (this) {
            if (file != null) {
                file.delete();
            }
            setFile(null);
            setStockExchange(null);
            setStocksToDisplay(null);
        }
    }

    /*
      берет из stockExchange только информацию о самой бирже.
      информацию об акциях берет из списка на печать, т.к. при поиске и сортировке
      этот список будет меняться
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(stockExchange.toString());
        for (Stock s :
                stocksToDisplay) {
            sb.append(s.toString());
        }
        return sb.toString();
    }
}
