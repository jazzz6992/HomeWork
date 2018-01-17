package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;

import java.io.File;

public abstract class AbstractParser implements Runnable {
    /*
    в соответствии с полиморфизмом все данные и методы парсера определены в
    абстрактном классе. сам метод parse реализован в конкретных парсерах
     */
    private ParseCompleteListener listener;
    private final Model model;

    public AbstractParser(ParseCompleteListener listener, Model model) {
        this.listener = listener;
        this.model = model;
    }

    abstract void parse(File sourse);

    public ParseCompleteListener getListener() {
        return listener;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void run() {
        synchronized (model) {
            parse(model.getFile());
        }
    }
}
