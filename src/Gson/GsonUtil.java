package Gson;

import com.google.gson.*;

import java.security.InvalidParameterException;
import java.util.Date;

/**
 * Created by xuch on 7/12/16.
 */
public class GsonUtil
{
    //private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,new TypeAdapter<Date>()).create();
    Gson gson = new Gson();
    private JsonParser jsonparser = new JsonParser();

    public JsonObject getJsonObject(String jsonStr) throws InvalidParameterException {

        JsonObject jsonObject = null;
        try {
            //Validate.notNull(jsonStr);
            jsonObject = (JsonObject) jsonparser.parse(jsonStr);
        } catch (JsonParseException | IllegalStateException | IllegalArgumentException | ClassCastException e) {
            throw new InvalidParameterException("Unable to create json object for - " + jsonStr);
        }

        return jsonObject;
    }

    public JsonElement getJsonElement(String jsonStr) throws InvalidParameterException {

        JsonElement jsonElement = null;
        try {
            //Validate.notNull(jsonStr);
            jsonElement = jsonparser.parse(jsonStr);
        } catch (JsonParseException | IllegalArgumentException e) {
            throw new InvalidParameterException("Unable to create json elemnt for - " + jsonStr);
        }

        return jsonElement;
    }

    public <T> String serialize(T t) {
        return gson.toJson(t);
    }

    public <T> T deserialize(String json, Class<T> clazz) throws InvalidParameterException {
        T target = null;
        try {
            target = (T) gson.fromJson(json, clazz);
        } catch (JsonParseException e) {
            throw new InvalidParameterException("The josn passed [" + json + "] is not in the correct formmat");
        }

        return target;
    }
}

