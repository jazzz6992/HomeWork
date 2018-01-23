package manager;

import manager.interfaces.DataChangedResultListener;
import manager.interfaces.ListForPrintChangeListener;
import manager.search.Searcher;
import manager.sort.Sorter;
import model.Model;
import model.entity.Stock;
import ui.interfaces.Ui;

import java.util.List;


public class Manager implements ListForPrintChangeListener, DataChangedResultListener {
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
    и передает эти данные модели для загрузки
     */
    public void getData(Ui.Source source) {
        String link;
        String fileName;
        if (source == Ui.Source.JSON) {
            link = JSON_LINK;
            fileName = JSON_FILE;
        } else {
            link = XML_LINK;
            fileName = XML_FILE;
        }
        model.getData(link, fileName);
    }

    /*
    запускает сортировку в новом потоке на основе объекта sorter, учитывая
    выбор пользователем критерия сортировки
    */
    public void sort(Ui.Action action) {
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
        model.setStocksToDisplay(stocks);
    }

    /*
    добавляет в список для отображения все доступные акции
     */
    public void showAll() {
        Thread showAllThread = new Thread(new Runnable() {
            @Override
            public void run() {
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
        });
        showAllThread.start();
    }

    //обнуляет данные модели
    public void resetAll() {
        model.clear();
    }

    //при оповещении о изменениях в модели отправляет ее на печать в ui
    @Override
    public void onSuccess() {
        ui.print(model.toString());
    }

    @Override
    public void onFail(String message) {
        ui.print(message);
    }

    //вычисляет среднюю цену акций из списка и отправляет информацию на печать
    public void average() {
        double average = 0;
        if (model.getStocksToDisplay() != null) {
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
}
