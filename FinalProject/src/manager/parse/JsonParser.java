package manager.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import manager.listeners.ParseCompleteListener;
import model.Model;
import model.entity.StockExchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

public class JsonParser extends AbstractParser {

    JsonParser(ParseCompleteListener listener, Model model) {
        super(listener, model);
    }

    //парсит данные из файла и оповещает слушателя в случае успеха или неудачи
    @Override
    public void parse(File sourse) {
        StockExchange stockExchange;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateGsonConverter());
        Gson gson = builder.create();
        try {
            stockExchange = gson.fromJson(new FileReader(sourse), StockExchange.class);
            getListener().onParseSuccess(stockExchange);
        } catch (FileNotFoundException e) {
            getListener().onParseFailed(e.getMessage());
        }
    }
}
