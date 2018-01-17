package manager.parse;

import manager.listeners.ParseCompleteListener;
import model.Model;

//реализация фабрики для получения json парсера
public class XmlParserFactory extends AbstractParserFactory {
    public XmlParserFactory(ParseCompleteListener listener, Model model) {
        super(listener, model);
    }

    @Override
    public AbstractParser getParser() {
        return new XmlParser(getListener(), getModel());
    }
}
