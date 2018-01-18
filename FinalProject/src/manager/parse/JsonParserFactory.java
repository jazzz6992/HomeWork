package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;

//реализация фабрики для получения json парсера
public class JsonParserFactory extends AbstractParserFactory {
    public JsonParserFactory(Model model, ParseCompleteListener listener) {
        super(model, listener);
    }

    @Override
    public AbstractParser getParser() {
        return new JsonParser(getModel(), getListener());
    }
}
