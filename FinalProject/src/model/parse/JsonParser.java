package model.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Model;
import model.entity.StockExchange;
import model.interfaces.ParseCompleteListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

public class JsonParser extends AbstractParser {

    JsonParser(Model model, ParseCompleteListener listener) {
        super(model, listener);
    }

    //парсит данные из файла и оповещает слушателя о результате
    @Override
    public void parse(File source) {
        StockExchange stockExchange;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateGsonConverter());
        Gson gson = builder.create();
        try {
            stockExchange = gson.fromJson(new FileReader(source), StockExchange.class);
            getListener().onParseSuccess(stockExchange);
        } catch (FileNotFoundException e) {
            getListener().onParseFailed(e.getMessage());
        }
    }
}
