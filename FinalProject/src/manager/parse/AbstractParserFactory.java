package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;
/*
выбран абстрактный класс вместо интерфейса, для того, чтобы можно было инициализировать в конструкторе необходимые
для конкретной фабрики данные о слушателе выполнения и модели
 */
public abstract class AbstractParserFactory {
    private ParseCompleteListener listener;
    private Model model;

    public AbstractParserFactory(ParseCompleteListener listener, Model model) {
        this.listener = listener;
        this.model = model;
    }

    public abstract AbstractParser getParser();

    public ParseCompleteListener getListener() {
        return listener;
    }

    public Model getModel() {
        return model;
    }
}
