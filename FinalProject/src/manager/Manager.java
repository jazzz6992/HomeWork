package manager;

import ui.buttonChoices.Action;
import ui.buttonChoices.Source;
import manager.download.Downloader;
import manager.listeners.DataChangedListener;
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


public class Manager implements DownloadCompleteListener, ListForPrintChangeListener, ParseCompleteListener, DataChangedListener {
    private static final String JSON_LINK = "http://kiparo.ru/t/stock.json";
    private static final String XML_LINK = "http://kiparo.ru/t/stock.xml";
    private static final String JSON_FILE = "src/content.json";
    private static final String XML_FILE = "src/content.xml";
    //под паттерн подходят строки, заканчивающиеся на .xml
    private static final String FILE_TYPE_XML = "^.+\\.xml$";
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
            factory = new XmlParserFactory(model, this);
        } else {
            factory = new JsonParserFactory(model, this);
        }
        parser = factory.getParser();
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
