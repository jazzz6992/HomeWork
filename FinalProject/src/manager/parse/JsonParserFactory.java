package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;

//реализация фабрики для получения json парсера
public class JsonParserFactory extends AbstractParserFactory {
    public JsonParserFactory(ParseCompleteListener listener, Model model) {
        super(listener, model);
    }

    @Override
    public AbstractParser getParser() {
        return new JsonParser(getListener(), getModel());
    }
}
