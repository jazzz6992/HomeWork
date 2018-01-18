package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;

//реализация фабрики для получения json парсера
public class XmlParserFactory extends AbstractParserFactory {
    public XmlParserFactory(Model model, ParseCompleteListener listener) {
        super(model, listener);
    }

    @Override
    public AbstractParser getParser() {
        return new XmlParser(getModel(), getListener());
    }
}
