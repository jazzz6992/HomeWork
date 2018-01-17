package manager;

import manager.buttonChoices.Action;
import manager.buttonChoices.Source;
import manager.download.Downloader;
import manager.listeners.DownloadCompleteListener;
import manager.listeners.ListForPrintChangeListener;
import manager.listeners.ParseCompleteListener;
import manager.parse.AbstractParser;
import manager.parse.AbstractParserFactory;
import manager.parse.JsonParserFactory;
import manager.parse.XmlParserFactory;
import manager.search.Searcher;
import manager.sort.Sorter;
import model.Model;
import model.entity.Stock;
import model.entity.StockExchange;
import ui.Ui;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Manager implements DownloadCompleteListener, ParseCompleteListener, ListForPrintChangeListener {
    private static final String JSON_LINK = "http://kiparo.ru/t/stock.json";
    private static final String XML_LINK = "http://kiparo.ru/t/stock.xml";
    //под паттерн подходят строки, заканчивающиеся на .xml
    private static final String FILE_TYPE_XML = "^.+\\.xml$";
    private Ui ui;
    private final Model model;
    //ленивая инициализация тут не нужна, т.к. менеджер будет инициализирован в любом случае
    private static final Manager MANAGER = new Manager();

    private Manager() {
        model = new Model();
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
            fileName = "src/content.json";
        } else {
            link = XML_LINK;
            fileName = "src/content.xml";
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
    инициализирует переменную абстрактной фабрики конкретной реализацией в
    соответствии с выбором пользователя, далее получает нужный парсер
    и запускает на его основе новый поток
     */
    private void parse() {
        AbstractParser parser;
        AbstractParserFactory factory;
        Pattern pattern = Pattern.compile(FILE_TYPE_XML);
        Matcher matcher = pattern.matcher(model.getFile().getName());
        if (matcher.matches()) {
            factory = new XmlParserFactory(this, model);
        } else {
            factory = new JsonParserFactory(this, model);
        }
        parser = factory.getParser();
        Thread parseThread = new Thread(parser);
        parseThread.start();
    }

    /*
    инициализирует поля модели объектами, созданными при парсинге, выводит
    информацию на печать в ui и оповещает другие потоки, которые, возможно ожидают данные
     */
    @Override
    public void onParseSuccess(StockExchange stockExchange) {
        synchronized (model) {
            model.setStockExchange(stockExchange);
            model.setStocksToDisplay(stockExchange.getStock());
            showAll();
            model.notifyAll();
        }
    }

    //выводит в ui информацию об ощибке
    @Override
    public void onParseFailed(String message) {
        ui.print(message);
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
//принимает измененный список акций для отображения и отправляет его на печать
    @Override
    public void onListForPrintChanged(List<Stock> stocks) {
        showCurrentList(stocks);
    }

    private void showCurrentList(List<Stock> stocks) {
        synchronized (model) {
            model.setStocksToDisplay(stocks);
            ui.print(model.toString());
        }
    }

    /*
    добавляет в список для отображения все доступные акции и выводит информацию на печать
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
            ui.print(model.toString());
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
}
