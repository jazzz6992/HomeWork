package manager;

import entity.StockExchange;
import manager.download.DownloadCompleteListener;
import manager.download.Downloader;
import manager.parse.AbstractParser;
import manager.parse.JsonParser;
import manager.parse.ParseCompleteListener;
import manager.parse.XmlParser;
import ui.Ui;

import java.io.File;

public class Manager implements ParseCompleteListener, DownloadCompleteListener {
    private File file;
    private StockExchange stockExchange;
    private Ui ui;

    public Manager(Ui ui) {
        this.ui = ui;
    }

    public void getFile(String link, String fileName) {
        Downloader downloader = new Downloader(link, fileName, this);
        Thread downloadThread = new Thread(downloader);
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
        System.out.println(this.stockExchange);
    }

    @Override
    public void onParseFailed() {

    }

    @Override
    public void onDownloadSuccess(File file) {
        this.file = file;
        parse();
    }

    @Override
    public void onDownloadFailed() {
        System.out.println("failed");
    }
}
