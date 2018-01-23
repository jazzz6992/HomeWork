package model.parse;

import model.Model;
import model.listeners.ParseCompleteListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleParserFactory {
    //под паттерн подходят строки, заканчивающиеся на .xml
    private static final String FILE_TYPE_XML = "^.+\\.xml$";

    public static AbstractParser createParser(Model model, ParseCompleteListener listener) {
        Pattern pattern = Pattern.compile(FILE_TYPE_XML);
        Matcher matcher = pattern.matcher(model.getFile().getName());
        AbstractParser parser;
        if (matcher.matches()) {
            parser = new XmlParser(model, listener);
        } else {
            parser = new JsonParser(model, listener);
        }
        return parser;
    }
}
