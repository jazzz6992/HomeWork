package l9;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGsonConverter implements JsonDeserializer<Date> {
    private final String[] formats = {"yyyy-MM-dd HH", "yyyy-MM-dd"};

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        for (String format : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(jsonElement.getAsString());
            } catch (Exception e) {

            }
        }
        return null;
    }
}
