package manager;

import manager.download.Downloader;
import manager.listeners.DataChangedListener;
import manager.listeners.DownloadCompleteListener;
import manager.listeners.ListForPrintChangeListener;
import manager.listeners.ParseCompleteListener;
import manager.parse.AbstractParser;
import manager.parse.SimpleParserFactory;
import manager.search.Searcher;
import manager.sort.Sorter;
import model.Model;
import model.entity.Stock;
import model.entity.StockExchange;
import ui.Ui;
import ui.buttonChoices.Action;
import ui.buttonChoices.Source;

import java.io.File;
import java.util.List;


public class Manager implements DownloadCompleteListener, ListForPrintChangeListener, ParseCompleteListener, DataChangedListener {
    private static final String JSON_LINK = "http://kiparo.ru/t/stock.json";
    private static final String XML_LINK = "http://kiparo.ru/t/stock.xml";
    private static final String JSON_FILE = "src/content.json";
    private static final String XML_FILE = "src/content.xml";
    private Ui ui;
    private final Model model;
    //ленивая инициализация тут не нужна, т.к. менеджер будет инициализирован в любом случае
    private static final Manager MANAGER = new Manager();

    private Manager() {
        model = new Model(this);
    }

    //инициализация в конструкторе невозможна, т.к. используется singleton
    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public static Manager getInstance() {
        return MANAGER;
    }

    /*
    устанавливает ссылку для загрузки и имя файла в соответствии с выбором пользователя
    создает загрузчик и запускает на его основе новый поток
     */
    public void getFile(Source source) {
        String link;
        String fileName;
        if (source == Source.JSON) {
            link = JSON_LINK;
            fileName = JSON_FILE;
        } else {
            link = XML_LINK;
            fileName = XML_FILE;
        }
        Downloader downloader = new Downloader(link, fileName, this, model);
        Thread downloadThread = new Thread(downloader, "download thread");
        downloadThread.start();
    }

    //в случае успешной загрузки инициализирует поле модели и начинает парсинг
    @Override
    public void onDownloadSuccess(File file) {
        synchronized (model) {
            model.setFile(file);
            parse();
        }
    }

    //отсылает на печать сообщение об ощибке
    @Override
    public void onDownloadFailed(String message) {
        ui.print(message);
    }

    /*
    инициализирует переменную абстрактного парсера конкретной реализацией в зависимости от состояния модели
    и запускает поток на основе созданного парсера
     */
    private void parse() {
        AbstractParser parser;
        parser = SimpleParserFactory.createParser(model, this);
        Thread parseThread = new Thread(parser);
        parseThread.start();
    }

    /*
    запускает сортировку в новом потоке на основе объекта sorter, учитывая
    выбор пользователем критерия сортировки
    */
    public void sort(Action action) {
        Thread sortThread = new Thread(new Sorter(this, model, action));
        sortThread.start();
    }

    /*
    запускает поиск в новом потоке на основе объекта searcher, в случае, если ключ не нулевой длинны
    */
    public void search(String key) {
        if (key != null && !key.equals("")) {
            Thread searchThread = new Thread(new Searcher(this, key, model));
            searchThread.start();
        }
    }

    //принимает измененный список акций для отображения и обновляет модель
    @Override
    public void updateListForPrint(List<Stock> stocks) {
        showCurrentList(stocks);
    }

    private void showCurrentList(List<Stock> stocks) {
        synchronized (model) {
            model.setStocksToDisplay(stocks);
        }
    }

    /*
    добавляет в список для отображения все доступные акции
     */
    public void showAll() {
        synchronized (model) {
            while (model.getStockExchange() == null) {
                try {
                    model.wait();
                } catch (InterruptedException ignored) {
                }
            }
            model.setStocksToDisplay(model.getStockExchange().getStock());
        }
    }

    //обнуляет данные модели
    public void resetAll() {
        synchronized (model) {
            model.getFile().delete();
            model.setFile(null);
            model.setStockExchange(null);
            model.setStocksToDisplay(null);
        }
    }

    //при оповещении о изменениях в модели отправляет ее на печать в ui
    @Override
    public void update() {
        ui.print(model.toString());
    }


    @Override
    public void onParseSuccess(StockExchange stockExchange) {
        model.setStockExchange(stockExchange);
    }

    @Override
    public void onParseFailed(String message) {
        ui.print(message);
    }

    public void average() {
        double average = 0;
        for (Stock s :
                model.getStocksToDisplay()) {
            average += s.getBid();
        }
        average /= model.getStocksToDisplay().size();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(model.toString())
                .append("\n")
                .append("Average price for selected stocks is ")
                .append(String.format("%.3f", average));
        ui.print(stringBuilder.toString());
    }
}
