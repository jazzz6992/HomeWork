package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;

/*
выбран абстрактный класс вместо интерфейса, для того, чтобы можно было инициализировать в конструкторе необходимые
для конкретной фабрики данные о слушателе выполнения и модели
 */
public abstract class AbstractParserFactory {
    private Model model;
    private ParseCompleteListener listener;

    AbstractParserFactory(Model model, ParseCompleteListener listener) {
        this.model = model;
        this.listener = listener;
    }

    public abstract AbstractParser getParser();

    public Model getModel() {
        return model;
    }

    public ParseCompleteListener getListener() {
        return listener;
    }
}
