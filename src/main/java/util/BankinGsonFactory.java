package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

/**
 * Created on 09/10/16.<br/>
 */
public class BankinGsonFactory {

    public static final DecimalFormat bankinDecimalFormat = new DecimalFormat("###.##");

    public Gson getJson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .registerTypeAdapter(Double.class, new DoubleDeserializer())
                .create();
    }

    private class DoubleDeserializer implements JsonSerializer<Double> {
        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(bankinDecimalFormat.format(src));
        }
    }
}
