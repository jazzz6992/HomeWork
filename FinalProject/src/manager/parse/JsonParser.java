package manager.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.StockExchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

public class JsonParser extends AbstractParser {

    public JsonParser(ParseCompleteListener listener, File file) {
        super(listener, file);
    }

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
            e.printStackTrace();
            getListener().onParseFailed();
        }
    }

    @Override
    public void run() {
        parse(getFile());
    }
}
